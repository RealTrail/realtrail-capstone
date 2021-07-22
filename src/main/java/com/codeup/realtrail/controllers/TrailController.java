package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.PictureURLsRepository;
import com.codeup.realtrail.daos.TrailCommentsRepository;
import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.models.*;
import com.codeup.realtrail.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class TrailController{
    private TrailsRepository trailsDao;
    private TrailCommentsRepository trailCommentsDao;
    private PictureURLsRepository pictureURLSDao;
    private UserService userService;

    // importing mapbox token
    @Value("pk.eyJ1Ijoia2FjaGlrYWNoaWN1aSIsImEiOiJja25hanJ6ZnMwcHpnMnZtbDZ1MGh5dms1In0.JAsEFoNV2QP1XXVWXlfQxA")
    private String mapboxToken;

    public TrailController(TrailsRepository trailsDao, TrailCommentsRepository trailCommentsDao, PictureURLsRepository pictureURLSDao, UserService userService) {
        this.trailsDao = trailsDao;
        this.trailCommentsDao = trailCommentsDao;
        this.pictureURLSDao = pictureURLSDao;
        this.userService = userService;
    }

    // showTrail.html- shows individual trail with all trail details
    @GetMapping("/trails/{id}")
    public String individualTrailPage(@PathVariable Long id, Model model, Principal principal){
        Trail trail = trailsDao.getById(id);
        List<TrailComment> trailComments = trailCommentsDao.getAllByTrailId(id);
        model.addAttribute("title", "Trail Details");
        model.addAttribute("trailId", id);
        model.addAttribute("trail", trail);
        model.addAttribute("trailComments", trailComments);
        model.addAttribute("mapboxToken", mapboxToken);
        model.addAttribute("postUrl", "/trails/" + id + "/comment");
        if (!trail.getTrailComments().isEmpty()) {
            long total = 0L;
            for (TrailComment comment : trail.getTrailComments()) {
                total += comment.getRating();
            }
            float average = (float) total / trail.getTrailComments().size();
            trail.setRating(average);
            model.addAttribute("average", average);
        }
        if (principal != null) {
            model.addAttribute("loggedInUser", userService.getLoggedInUser());
        }
        return "trails/showTrail";
    }

    @PostMapping("/trails/create")
    @ResponseBody
    public Trail createTrail(@Valid @RequestBody Trail trail) {
        Trail trailSaved;
        trailSaved = trailsDao.save(trail);
        for (PictureURL url : trail.getTrailImages()) {
            url.setTrail(trailSaved);
            pictureURLSDao.save(url);
        }

        return trailSaved;
    }

    @GetMapping("/search-trail")
    public String getSearchedTrail(@RequestParam (value="keyword", required = false) String keyword, Model model) {
        List<Trail> trails = trailsDao.findByName(keyword);

        if (trails.isEmpty()) {
            Set<Trail> trailSet = new HashSet<>();
            List<String> keywords = Arrays.asList(keyword.split(" "));
            for (String string : keywords) {
                System.out.println("string = " + string);
                trailSet.addAll(trailsDao.findByName(string));  // combine the trails found together without duplicates
            }
            List<Trail> trailList = new ArrayList<>(trailSet);
            trailList.sort(new Comparator<Trail>() {
                @Override
                public int compare(Trail trail1, Trail trail2) {
                    String name1 = trail1.getName();
                    String name2 = trail2.getName();
                    return name1.compareToIgnoreCase(name2);
                }
            });

            model.addAttribute("trails", trailList);
            return "home";
        } else {
            model.addAttribute("trails", trails);
            return "home";
        }
    }

    @GetMapping("/trails/filter")
    public String filterDifficultyLevelOrType(Model model,
            @RequestParam(name = "difficulty", required = false) String diffLevel,
            @RequestParam(name = "type", required = false) String type) {

        System.out.println("diffLevel = " + diffLevel);
        System.out.println("type = " + type);
        List<Trail> trails = null;
        if (type.length() == 0) {
            trails = trailsDao.findByDifficultyLevel(diffLevel);
        } else if (diffLevel.length() == 0) {
            trails = trailsDao.findByType(type);
        } else {
            trails = trailsDao.findByDifficultyLevelAndType(diffLevel, type);
        }

        trails.sort((trail1, trail2) -> {
            String name1 = trail1.getName();
            String name2 = trail2.getName();
            return name1.compareToIgnoreCase(name2);
        });
        model.addAttribute("trails", trails);
        return "home";
    }

    @PostMapping("/trails/{id}/comment")
    public String saveTrailComment(@PathVariable long id,
                                   @RequestParam(name = "rating") String rating,
                                   @RequestParam(name = "content") String content){
        User user = userService.getLoggedInUser();
        Trail trail = trailsDao.getById(id);
        LocalDateTime date = LocalDateTime.now();
        TrailComment trailComment = new TrailComment(date, Integer.parseInt(rating), content, user, trail);
        trailCommentsDao.save(trailComment);
        return "redirect:/trails/" + id;
    }

    @PostMapping("/trails/{id}/comment/{cid}/delete")
    public String deleteTrailComment(@PathVariable Long id, @PathVariable Long cid) {
        User user =  userService.getLoggedInUser();
        TrailComment trailcomment = trailCommentsDao.getById(cid);
        if(user.isAdmin() || trailcomment.getOwner().getId()== user.getId()){
            trailCommentsDao.deleteById(cid);
        }
        return "redirect:/trails/" + id;
    }
}

