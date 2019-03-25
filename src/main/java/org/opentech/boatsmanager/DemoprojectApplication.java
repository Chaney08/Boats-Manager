package org.opentech.boatsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"org.opentech.boatsmanager.controller", "org.opentech.boatsmanager.config","org.opentech.boatsmanager.service","org.opentech.boatsmanager.repository"
        ,"org.opentech.boatsmanager.utils"})
public class DemoprojectApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoprojectApplication.class, args);
    }
}
