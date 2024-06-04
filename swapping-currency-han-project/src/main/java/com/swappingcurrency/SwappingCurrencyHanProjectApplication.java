package com.swappingcurrency;

import com.cloudinary.Cloudinary;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@Tag(name = "Swapping Currency Han Project", description = "This project is used to swap crypto currencies from one another")
public class SwappingCurrencyHanProjectApplication {

	@Bean
	public Cloudinary getCloudinary(){
		return new Cloudinary();
	}

	public static void main(String[] args) {
		SpringApplication.run(SwappingCurrencyHanProjectApplication.class, args);
	}

}