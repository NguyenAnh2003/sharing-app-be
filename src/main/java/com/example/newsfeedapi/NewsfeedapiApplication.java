package com.example.newsfeedapi;

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

			User u = new User("nguyenanh", email, "https://avatarURL", Gender.MALE, wishlist);

			Query query = new Query();
			query.addCriteria(Criteria.where("gmail").is(email));

			List<User> users = mongoTemplate.find(query, User.class);

			try {
				if(users.isEmpty()) {
					System.out.println("NONE");
					repository.insert(u);
				} else {
					System.out.println("Already exists" + users);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
	}
}
