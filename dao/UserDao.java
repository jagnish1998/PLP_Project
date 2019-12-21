package com.capgemini.hotelmanagement.dao;

import java.util.List;

import com.capgemini.hotelmanagement.bean.UserBean;
import com.capgemini.hotelmanagement.exception.HotelManagementException;

public interface UserDao {
	
	public boolean addUser(UserBean userBean) throws HotelManagementException;
	
	public UserBean login(String email,String password) throws HotelManagementException;
	
	public List<UserBean> getAllUser() throws HotelManagementException;
	
	public List<UserBean> getuser(String type) throws HotelManagementException;
	
}
