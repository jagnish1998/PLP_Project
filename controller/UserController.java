package com.capgemini.hotelmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.hotelmanagement.bean.HotelResponseBean;
import com.capgemini.hotelmanagement.bean.UserBean;
import com.capgemini.hotelmanagement.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/adduser")
	public HotelResponseBean registerUser(@RequestBody UserBean userBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		userBean.setUserType("user");
		boolean isAdded = userService.addUser(userBean);
		if(isAdded) {
			hotelResponseBean.setStatuscode(200);
			hotelResponseBean.setMessage("Success");
			hotelResponseBean.setDescription("User Register Successfully");
		}else {
			hotelResponseBean.setStatuscode(400);
			hotelResponseBean.setMessage("Failer");
			hotelResponseBean.setDescription("Email Already Exist Please Try with diffrent email");
		}
		return hotelResponseBean;
	}

	@PostMapping("/register")
	public HotelResponseBean register(@RequestBody UserBean userBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		boolean isAdded = userService.addUser(userBean);
		if(isAdded) {
			hotelResponseBean.setStatuscode(200);
			hotelResponseBean.setMessage("Success");
			hotelResponseBean.setDescription("User Register Successfully");
		}else {
			hotelResponseBean.setStatuscode(400);
			hotelResponseBean.setMessage("Failer");
			hotelResponseBean.setDescription("Email Already Exist Please Try with diffrent email");
		}
		return hotelResponseBean;
	}
	
	@PostMapping("/login")
	public HotelResponseBean login(@RequestBody UserBean userBean) {
		String email=userBean.getEmail();
		String password=userBean.getPassword();
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		UserBean userBean2=userService.login(email, password);
		if(userBean2!=null) {
			hotelResponseBean.setStatuscode(200);
			hotelResponseBean.setMessage("Success");
			hotelResponseBean.setDescription("User Login Successfully...");
			hotelResponseBean.setUserBean(userBean2);
		}else {
			hotelResponseBean.setStatuscode(400);
			hotelResponseBean.setMessage("Failer");
			hotelResponseBean.setDescription("Unable to Login..");
		}
		return hotelResponseBean;
	}
	
	@GetMapping("/getAllUser")
	public HotelResponseBean getAllUser() {
		List<UserBean> userList = userService.getAllUser();
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		if(userList != null && !userList.isEmpty()) {
			hotelResponseBean.setStatuscode(200);
			hotelResponseBean.setMessage("Success");
			hotelResponseBean.setDescription("User List Found...");
			hotelResponseBean.setUserList(userList);
		}else {
			hotelResponseBean.setStatuscode(400);
			hotelResponseBean.setMessage("Failer");
			hotelResponseBean.setDescription("No User Found...");
		}
		return hotelResponseBean;
	}
	
	@GetMapping("/getUser")
	public HotelResponseBean getUser(String userType) {
		List<UserBean> userList = userService.getuser(userType);
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		if(userList != null && !userList.isEmpty()) {
			hotelResponseBean.setStatuscode(200);
			hotelResponseBean.setMessage("Success");
			hotelResponseBean.setDescription("User List Found...");
			hotelResponseBean.setUserList(userList);
		}else {
			hotelResponseBean.setStatuscode(400);
			hotelResponseBean.setMessage("Failer");
			hotelResponseBean.setDescription("No User Found...");
		}
		return hotelResponseBean;
	}
}
