package com.capgemini.hotelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hotelmanagement.bean.UserBean;
import com.capgemini.hotelmanagement.dao.UserDao;
import com.capgemini.hotelmanagement.exception.HotelManagementException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public boolean addUser(UserBean userBean) {
		try {
			return userDao.addUser(userBean);
		} catch (HotelManagementException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	@Override
	public UserBean login(String email, String password) {
		try {
			return userDao.login(email, password);
		} catch (HotelManagementException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<UserBean> getAllUser() {
		try {
			return userDao.getAllUser();
		} catch (HotelManagementException e) {
			System.err.println(e.getMessage());
		}
		return null;}

	@Override
	public List<UserBean> getuser(String type) {
		try {
			return userDao.getuser(type);
		} catch (HotelManagementException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
