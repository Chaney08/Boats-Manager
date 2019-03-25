package org.opentech.boatsmanager.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opentech.boatsmanager.model.Boat;
import org.opentech.boatsmanager.repository.BoatRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource(
        locations = "classpath:applicationTest.properties")
public class BoatControllerTests {

    @Resource
    private BoatRepository boatRepo;

    Boat boat = new Boat();


    @Before
    public void setup() {
        boat.setBoatName("Test");
        boat.setDescription("This is a description");
        boat.setWeight(1.0);
        boatRepo.save(boat);
    }
    @Test
    public void boatRegistration() {

        //The save is done already so we just need to verify it worked
        Boat boat2 = boatRepo.findByBoatName("Test");
        assertEquals("Test", boat2.getBoatName());
    }

    @Test
    public void deleteBoat() {
        boatRepo.deleteBoatByBoatId(boat.getBoatId());
        assertNull(boatRepo.findByBoatName("Test"));
    }
    @Test
    public void boatUpdate(){
        boat.setBoatName("Changed name");
        boatRepo.save(boat);
        Boat boat2 = boatRepo.findByBoatName("Changed name");
        assertNull(boatRepo.findByBoatName("Test"));
        assertEquals("Changed name", boat2.getBoatName());
    }

}
