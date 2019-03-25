package org.opentech.boatsmanager.utils;

import org.opentech.boatsmanager.model.User;
import org.opentech.boatsmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Configurable
public class WebUtils {

    @Autowired
    private UserRepository appUserRepo;

    public User getUser(){
        return this.appUserRepo.findByUserName(getUserName());
    }

    public String getUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}