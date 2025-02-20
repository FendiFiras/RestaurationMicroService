package org.esprit.reservationmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ReservationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationMicroserviceApplication.class, args);
	}

}
