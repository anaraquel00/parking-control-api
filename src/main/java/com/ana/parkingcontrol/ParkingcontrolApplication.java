package com.ana.parkingcontrol;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableAutoConfiguration
@SpringBootApplication
public class ParkingcontrolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingcontrolApplication.class, args);
	}

}
