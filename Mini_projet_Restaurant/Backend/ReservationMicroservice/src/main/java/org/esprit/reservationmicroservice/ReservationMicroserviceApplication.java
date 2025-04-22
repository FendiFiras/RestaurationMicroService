package org.esprit.reservationmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients

public class ReservationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationMicroserviceApplication.class, args);
	}

}
