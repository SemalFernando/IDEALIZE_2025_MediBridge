package com.example.home.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dyyvrldxs",
                "api_key", "451951848716291",
                "api_secret", "ylc68msu88fax_K4TW5LcMvu2fs"
        ));
    }
}
