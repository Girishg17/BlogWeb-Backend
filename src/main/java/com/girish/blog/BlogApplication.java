package com.girish.blog;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {
	public static final Logger logger= (Logger) LoggerFactory.getLogger(BlogApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public  void run(String... args)throws Exception{
		logger.info("its my second logger");
	}

}
