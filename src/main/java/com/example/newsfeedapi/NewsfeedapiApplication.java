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
			ArrayList<String> wishlist = new ArrayList<String>();
			wishlist.add("w1");
			wishlist.add("w2");
			wishlist.add("w3");
			String email = "cunho@gmail.com";

			User u = new User("nguyenanh", email, "https://avatarURL", "male", wishlist);

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
