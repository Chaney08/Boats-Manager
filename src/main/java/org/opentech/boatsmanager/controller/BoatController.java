package org.opentech.boatsmanager.controller;

import org.opentech.boatsmanager.model.Boat;
import org.opentech.boatsmanager.model.User;
import org.opentech.boatsmanager.repository.BoatRepository;
import org.opentech.boatsmanager.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping(path="/boat")
public class BoatController {

    @Autowired
    private BoatRepository boatRepo;

    @Autowired
    private WebUtils webUtils;

    @RequestMapping(value = { "/", "/boatDashboard" })
    public String boatDashboard(Model model) {
        //Get the user and load their owned boats to the page
        User user = webUtils.getUser();
        model.addAttribute("boats", user.getOwnedBoats());
        return "boatTemplates/boatDashboard";
    }
    @GetMapping(value = "/boatRegistration")
    public String boatRegistration(Model model) {
        Boat boat= new Boat();
        model.addAttribute("boatRegistration", boat);
        return "boatTemplates/boatRegistration";
    }

    @PostMapping(value = "/boatRegistration")
    public String boatRegistration(@ModelAttribute("boatRegistration") @Valid Boat boat,
                                      BindingResult result) {

        Boat existing = boatRepo.findByBoatName(boat.getBoatName());
        if (existing != null) {
            result.rejectValue("boatName", null, "There is already a boat with that name");
        }

        if (result.hasErrors()) {
            return "boatTemplates/boatRegistration";
        }else {
            boat.setUser(webUtils.getUser());
            boatRepo.save(boat);
            return "redirect:/boat/boatDashboard";
        }
    }

    @Transactional
    @RequestMapping(value = "/deleteBoat", method = RequestMethod.GET)
    public String deleteBoat(@RequestParam(name="boatId") long idForDeletion) {
        boatRepo.deleteBoatByBoatId(idForDeletion);
        return "redirect:/boat/boatDashboard";
    }

    @GetMapping(value = "/boatUpdate")
    public String updateBoat(Model model,@RequestParam(name="boatId") int idForEditing) {
        Boat boat = boatRepo.findBoatByBoatId(idForEditing);
        model.addAttribute("boatUpdate", boat);
        return "boatTemplates/boatUpdate";
    }

    @PostMapping(value = "/boatUpdate")
    public String updateBoat(@ModelAttribute("boatUpdate") @Valid Boat boat,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "boatTemplates/boatUpdate";
        }else {
            boat.setUser(webUtils.getUser());
            boatRepo.save(boat);
            return "redirect:/boat/boatDashboard";
        }

    }


}
