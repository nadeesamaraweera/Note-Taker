package com.example.noteTaker.config;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.example.noteTaker")
@EnableWebMvc
@EnableJpaRepositories(basePackages = "com.example.noteTaker")
@EnableTransactionManagement
//multi part form data handle karann configuration active wenooo.
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, //file ekk uplaod karata passe
        maxFileSize = 1024 * 1024 * 10, //10MB   uplaod karana file eke uparima size eke
        maxRequestSize = 1024 * 1024 * 50  //50MB upload krana file athuluwa samastha resoure eke size eka
)
public class WebAppConfig {
}
