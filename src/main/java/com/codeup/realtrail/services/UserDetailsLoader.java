package com.codeup.realtrail.services;

import com.codeup.realtrail.daos.UsersRepository;
import com.codeup.realtrail.models.User;
import com.codeup.realtrail.models.UserWithRoles;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private UsersRepository usersDao;

    public UserDetailsLoader(UsersRepository usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        User user = null;
        if (string.contains("@")) {
            user = usersDao.findByEmail(string);
        } else {
            user = usersDao.findByUsername(string);
        }

        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + string);
        }

        return new UserWithRoles(user);
    }
}
