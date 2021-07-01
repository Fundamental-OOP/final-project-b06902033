package org.foop.finalproject.theMessageServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TheMessageServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
//		System.out.println(Utility.generateRoomId());
		SpringApplication.run(TheMessageServerApplication.class, args);
	}

}
