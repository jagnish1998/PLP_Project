package com.capgemini.hotelmanagement.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.hotelmanagement.bean.HotelResponseBean;
import com.capgemini.hotelmanagement.exception.HotelManagementException;

@RestControllerAdvice
public class UserAdviceController {

	@ExceptionHandler()
	public HotelResponseBean hotelException(HotelManagementException e) {
		HotelResponseBean response = new HotelResponseBean();
		response.setStatuscode(401);
		response.setMessage("Failer");
		response.setDescription(e.getMessage());
		return response;
	}
	
	
	
	
	
}
