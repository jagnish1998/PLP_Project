package com.capgemini.hotelmanagement.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBean {
	
	private int statusCode;
	private String message;
	private String description;
	private HotelBean hotelBean;
	private UserBean userBean;
	private List<UserBean> userList;
	private List<HotelBean> hotelList;
	

}
