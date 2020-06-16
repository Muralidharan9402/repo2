package com.infy.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.exception.InvalidCityException;
import com.infy.exception.InvalidEmailException;
import com.infy.exception.InvalidPhoneNumberException;
import com.infy.exception.InvalidUserNameException;
import com.infy.exception.MovieMaxException;
import com.infy.model.User;
import com.infy.repository.UserRepository;

@Service
public class MovieService {
	
	@Autowired
	private UserRepository userRepository;
	
	String regexName = "^[a-zA-Z]{3,25}";
	String regexEmail = "^[a-zA-Z0-9+_.-]+@(.+)$";
	String regexPhoneNumber = "^\\d{10}$";
	String regexCity = "^[a-zA-Z]{3,15}";
	
	public String registerUser(User user) throws MovieMaxException{
		
		String validateMessage = null;
		
		vaildateUser(user);
		validateMessage = userRepository.getUser();
		
		return validateMessage;
		
	}

	private void vaildateUser(User user) throws MovieMaxException{
		if(!isValidUserName(user.getName()))
			throw new InvalidUserNameException("RegistrationService.INVALID_USER_NAME");
		if(!isValidEmail(user.getEmail()))
			throw new InvalidEmailException("RegistrationService.INVALID_EMAIL");
		if(!isValidPhoneNumber(user.getPhoneNumber()))
			throw new InvalidPhoneNumberException("RegistrationService.INVALID_PHONE_NUMBER");
		if(!isValidCity(user.getCity()))
			throw new InvalidCityException("RegistrationService.INVALID_CITY");
		
		
	}

	private boolean isValidCity(String city) {
		// TODO Auto-generated method stub
		Boolean validCity = false;
		
		Pattern patternCity = Pattern.compile(regexCity);
		Matcher matchCity = patternCity.matcher(city);
		if(matchCity.matches())
			validCity = true;
		return validCity;
		
	}

	private boolean isValidPhoneNumber(Long phoneNumber) {
		// TODO Auto-generated method stub
		Boolean validPhoneNumber = false;
		
		Pattern patternPhoneNumber = Pattern.compile(regexPhoneNumber);
		Matcher matchPhoneNumber = patternPhoneNumber.matcher(String.valueOf(phoneNumber));
		if(matchPhoneNumber.matches())
			validPhoneNumber = true;
		return validPhoneNumber;
	}

	private boolean isValidEmail(String email) {
		// TODO Auto-generated method stub
		Boolean validEmail = false;
		
		Pattern patternEmail = Pattern.compile(regexEmail);
		Matcher matchEmail = patternEmail.matcher(email);
		if(matchEmail.matches())
			validEmail = true;
		return validEmail;
	}

	private boolean isValidUserName(String name) {
		// TODO Auto-generated method stub
		Boolean validName = false;
		
		Pattern patternName = Pattern.compile(regexName);
		Matcher matchName = patternName.matcher(name);
		if(matchName.matches())
			validName = true;
		return validName;
	}
	
	

}
