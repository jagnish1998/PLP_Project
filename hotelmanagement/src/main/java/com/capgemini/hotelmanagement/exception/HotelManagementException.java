package com.capgemini.hotelmanagement.exception;

public class HotelManagementException extends RuntimeException{
	String message ;

	public HotelManagementException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
