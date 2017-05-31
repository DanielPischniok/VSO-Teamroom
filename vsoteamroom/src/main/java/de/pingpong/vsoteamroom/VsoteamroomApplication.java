package de.pingpong.vsoteamroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VsoteamroomApplication {
	
	public static final String REST_COMMON_PATH = "/vso";

	public static void main(String[] args) {
		SpringApplication.run(VsoteamroomApplication.class, args);
	}
}
