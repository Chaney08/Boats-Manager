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




    @Before
    public void setup() {

    }

    @Test
    public void boatRegistration() {
        Boat boat = new Boat();
        boat.setBoatName("TestRegistration");
        boat.setDescription("This is a description");
        boat.setWeight(1.0);
        boatRepo.save(boat);

        //The save is done already so we just need to verify it worked
        Boat boat2 = boatRepo.findByBoatName("TestRegistration");
        assertEquals("TestRegistration", boat2.getBoatName());
    }
    @Test
    public void deleteBoat() {
        Boat boat = new Boat();
        boat.setBoatName("TestDeletion");
        boat.setDescription("This is a description");
        boat.setWeight(1.0);
        boatRepo.save(boat);
        boatRepo.deleteBoatByBoatId(boat.getBoatId());
        assertNull(boatRepo.findByBoatName("TestDeletion"));
    }
    @Test
    public void boatUpdate(){
        Boat boat = new Boat();
        boat.setBoatName("TestUpdate");
        boat.setDescription("This is a description");
        boat.setWeight(1.0);
        boatRepo.save(boat);

        Boat boat2 = boatRepo.findByBoatName("TestUpdate");
        assertEquals("TestUpdate", boat2.getBoatName());

        boat.setBoatName("Changed name");
        boatRepo.save(boat);
        Boat boat3 = boatRepo.findByBoatName("Changed name");
        assertNull(boatRepo.findByBoatName("TestUpdate"));
        assertEquals("Changed name", boat3.getBoatName());
    }

}
