package com.capgemini.hotelmanagement.service;

import java.util.List;

import com.capgemini.hotelmanagement.bean.UserBean;

public interface UserService {

	public boolean addUser(UserBean userBean);

	public UserBean login(String email, String password);

	public List<UserBean> getAllUser();

	public List<UserBean> getuser(String type);

}
