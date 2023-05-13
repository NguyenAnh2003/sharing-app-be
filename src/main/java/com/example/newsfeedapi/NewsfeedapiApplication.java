package com.example.newsfeedapi;

import com.example.newsfeedapi.user.User;
import com.example.newsfeedapi.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class NewsfeedapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsfeedapiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			String email = "cunho@gmail.com";

			User u = new User("nguyenanh", email,"password", "https://avatarURL", "male");

//			ctrlAltM(repository, mongoTemplate, email, u);
			repository.findUsersByName("nguyenanh")
					.ifPresentOrElse(s -> {
						System.out.println("User found" + u);
					}, () -> {
						System.out.println("Inserting user" + u);
					});

		};
	}

}
