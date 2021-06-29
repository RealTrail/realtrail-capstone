package com.codeup.realtrail.controllers;

import com.codeup.realtrail.daos.TrailsRepository;
import com.codeup.realtrail.models.Event;
import com.codeup.realtrail.models.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class TrailController{
    private TrailsRepository trailsDao;



    @GetMapping("/trails/{id}")
    public String individualTrailPage(@PathVariable Long id, Model model){
        Trail trail = trailsDao.getById(id);
        model.addAttribute("trailId", id);
        model.addAttribute("trail", trail);

        return "trails/showTrail";
    }

}

