package me.bajmo.kanbaniser.services.boards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BoardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardsApplication.class, args);
	}

}
