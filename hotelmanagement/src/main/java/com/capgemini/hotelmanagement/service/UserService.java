package com.capgemini.hotelmanagement.service;

import java.util.List;

import com.capgemini.hotelmanagement.bean.HotelBean;
import com.capgemini.hotelmanagement.bean.UserBean;

public interface UserService {

	public boolean addUser(UserBean userBean);

	public UserBean login(String email, String password);

	public boolean updateUserProfile(UserBean userBean);

	public List<UserBean> getAllUser();

	public List<UserBean> getUser(String type);
	
	public List<HotelBean> getHotel(String hotelLocation);
	
	public boolean changePassword(UserBean userBean, String newPassword);
	
	public UserBean viewProfile(String email);
	
	public boolean deleteUser(int userId);
	
	
}
