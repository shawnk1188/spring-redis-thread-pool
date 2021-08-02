package com.forgerock.codingexercise.sample;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableRedisRepositories
@EnableAsync
public class SampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}
}
