package com.example.socialapi.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Autowired
    private Environment environment;
    @Bean("mycloudinary")
    public Cloudinary cloudinaryConfig() {
        /* config info */
        String cloudname = environment.getProperty("cloudname");
        String apikey = environment.getProperty("apikey");
        String apisecret = environment.getProperty("apisecret");
        /* config setup */
        Map config = new HashMap();
        config.put("cloud_name", cloudname);
        config.put("api_key", apikey);
        config.put("api_secret", apisecret);
        return new Cloudinary(config);
    }
}
