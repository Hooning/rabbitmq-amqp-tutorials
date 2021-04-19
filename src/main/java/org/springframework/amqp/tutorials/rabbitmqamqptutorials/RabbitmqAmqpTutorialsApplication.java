package org.springframework.amqp.tutorials.rabbitmqamqptutorials;

import org.springframework.amqp.tutorials.RabbitAmqpTutorialsRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class RabbitmqAmqpTutorialsApplication {

	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return args -> {
			System.out.println("This app uses Spring Profiles to control its behavior");
			System.out.println("Sample usage: java -jar" +
							"rabbit-tutorials.jar" +
							"--spring.profiles.active=hello-world,sender");
		};
	}

	@Profile("!usage_message")
	@Bean
	public CommandLineRunner tutorial() {
		System.out.println("## RabbitAmqpTutorialsRunner !");
		return new RabbitAmqpTutorialsRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqAmqpTutorialsApplication.class, args);
	}

}