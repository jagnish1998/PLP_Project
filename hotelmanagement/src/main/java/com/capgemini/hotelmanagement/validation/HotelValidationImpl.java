package com.capgemini.hotelmanagement.validation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class HotelValidationImpl implements HotelValidation{
	Pattern pattern=null;
    Matcher matcher=null;
	@Override
	public boolean emailValidation(String email) {
		
		pattern=Pattern.compile("\\w+\\@\\w+\\.\\w+");
		matcher=pattern.matcher(email);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	@Override
	public boolean passwordValidation(String password) {
		pattern=Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,15}");
		matcher=pattern.matcher(password);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}

	
}
