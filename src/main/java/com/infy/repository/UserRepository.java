package com.infy.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	private String successMessage = "UserRepository.SUCCESS";
	//Map<String, String> movieDetails = new HashMap<>();
	
	

	public String getUser() {
		// TODO Auto-generated method stub
		
		return successMessage;
	}
	
}
