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



}
