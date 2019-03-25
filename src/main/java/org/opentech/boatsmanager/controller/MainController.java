package org.opentech.boatsmanager.controller;

import org.springframework.security.core.userdetails.User;
import org.opentech.boatsmanager.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping(path="/")
public class MainController {

    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "userTemplates/loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        return "userTemplates/loginPage";
    }
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        return "403Page";
    }
}