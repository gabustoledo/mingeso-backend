package com.mingeso.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.mingeso.backend.uploadingfiles.storage.StorageProperties;
import com.mingeso.backend.uploadingfiles.storage.StorageService;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class BackendApplication {

	public static void main(String[] args) {


    // Create a Logger
    Logger logger = Logger.getLogger(BackendApplication.class.getName());

		SpringApplication.run(BackendApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return args -> {
			storageService.init();
		};
	}

}
