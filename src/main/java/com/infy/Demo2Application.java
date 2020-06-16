package com.infy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.infy.model.User;
import com.infy.service.MovieService;

@SpringBootApplication
@PropertySource(value = {"classpath:configuration.properties"})
public class Demo2Application implements CommandLineRunner {

	@Autowired
	private Environment environment;
	
	@Autowired
	ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	
	
	@Override
	public void run(String... args) throws Exception {
		
		
		MovieService movieService = (MovieService) applicationContext.getBean("movieService");
		
		Map<String, String> movieDetails = new HashMap<>();
		movieDetails.put("M1001", "SpiderMan");
		movieDetails.put("M1002", "Thor");
		movieDetails.put("M1003", "Logan");
		movieDetails.put("M1004", "Conjuring2");
		movieDetails.put("M1005", "StarWars");
		
		try {
			
			User user = new User();
			
			Scanner input = new Scanner(System.in);
			System.out.println("Enter your Name:");
			String uName = input.next();
			System.out.println("Enter your EmailId:");
			String uEmail = input.next();
			System.out.println("Enter your PhoneNumber:");
			long uPhoneNumber = input.nextLong();
			System.out.println("Enter your City:");
			String uCity = input.next();
			
			user.setName(uName);
			user.setEmail(uEmail);
			user.setPhoneNumber(uPhoneNumber);
			user.setCity(uCity);
			
			System.out.println("Select a movie to book:");
			
			movieDetails.forEach((k,v)->System.out.println(".\t"+v));
			String movieName = input.next();
			
			   /*//String map = movieDetails.entrySet().stream()
						.filter(entry -> movieName.equals(entry.getValue()))
						.map(Map.Entry::getKey)
						.findFirst().get();*/
				
				String movieServiceMessage = movieService.registerUser(user);
				System.out.println("Hi"+uName);
				System.out.println(environment.getProperty(movieServiceMessage)+""+movieDetails.entrySet().stream()
						.filter(entry -> movieName.equals(entry.getValue()))
						.map(Map.Entry::getKey)
						.findFirst().get());
				
			   
			
			
		}
		catch(Exception exception) {
			System.out.println(environment.getProperty(exception.getMessage()));
		}
		
	}

}
