package org.opentech.boatsmanager.controller;

import org.junit.Before;
import org.junit.Test;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.opentech.boatsmanager.model.Boat;
import org.opentech.boatsmanager.model.User;
import org.opentech.boatsmanager.repository.BoatRepository;
import org.opentech.boatsmanager.utils.WebUtils;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//This test class tests that all pages are loaded when required
public class BoatControllerWebTests {

    @Mock
    private WebUtils webUtils;
    @Mock
    private BoatRepository boatRepo;

    @InjectMocks
    private BoatController boatController;

    private MockMvc mockMvc;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(boatController).build();
    }

    @Test
    public void testDashboard() throws Exception {
        User user = new User();
        List<Boat> boatList = new ArrayList<>();
        boatList.add(new Boat());
        user.setOwnedBoats(boatList);

        when(webUtils.getUser()).thenReturn((User) user);
           mockMvc.perform(get("/boat/"))
                .andExpect(status().isOk())
                .andExpect(view().name("boatTemplates/boatDashboard"))
                .andExpect(model().attribute("boats",hasSize(1)));
    }

    @Test
    public void testRegistration() throws Exception {
        mockMvc.perform(get("/boat/boatRegistration"))
                .andExpect(status().isOk())
                .andExpect(view().name("boatTemplates/boatRegistration"))
                .andExpect(model().attribute("boatRegistration",instanceOf(Boat.class)));
    }

    @Test
    public void testBoatUpdate() throws Exception {
        Long boatId = 1l;
        Boat boat = new Boat();

        when(boatRepo.findBoatByBoatId(boatId)).thenReturn((Boat) boat);
        mockMvc.perform(get("/boat/boatUpdate?boatId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("boatTemplates/boatUpdate"))
                .andExpect(model().attribute("boatUpdate",instanceOf(Boat.class)));
    }


}
