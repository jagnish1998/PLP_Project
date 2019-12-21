package com.capgemini.hotelmanagement.exception;

public class HotelManagementException extends Exception {

	String msg ;

	public HotelManagementException(String msg) {
		super();
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}
	
}
