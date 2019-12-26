package com.capgemini.hotelmanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.hotelmanagement.bean.HotelBean;
import com.capgemini.hotelmanagement.bean.HotelResponseBean;
import com.capgemini.hotelmanagement.bean.UserBean;
import com.capgemini.hotelmanagement.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/userRegistration")
	public HotelResponseBean registerUser(@RequestBody UserBean userBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		userBean.setUserType("user");
		userService.addUser(userBean);
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("User Register Successfully");
		return hotelResponseBean;
	}// end of add User

	@PostMapping("/addEmployee")
	public HotelResponseBean register(@RequestBody UserBean userBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		userBean.setUserType("employee");
		userService.addUser(userBean);
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("Employee Added Successfully");
		return hotelResponseBean;
	}

	@PostMapping("/login")
	public HotelResponseBean login(@RequestBody UserBean userBean) {
		String email = userBean.getEmail();
		String password = userBean.getPassword();
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		UserBean userBean2 = userService.login(email, password);
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("User Login Successfully...");
		hotelResponseBean.setUserBean(userBean2);
		return hotelResponseBean;
	}// end of login

	@GetMapping("/viewProfile")
	public HotelResponseBean viewProfile(@RequestParam String email) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		UserBean userBean = userService.viewProfile(email);
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("User Profile Found");
		hotelResponseBean.setUserBean(userBean);
		return hotelResponseBean;

	}

	@PostMapping("/updateUserProfile")
	public HotelResponseBean updateProfile(@RequestBody UserBean userBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		userService.updateUserProfile(userBean);
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("User Updated Successfully");
		return hotelResponseBean;
	}

	@GetMapping("/getAllUser")
	public HotelResponseBean getAllUser() {
		List<UserBean> userList = userService.getAllUser();
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("User List Found...");
		hotelResponseBean.setUserList(userList);
		return hotelResponseBean;
	}

	@GetMapping("/getUser")
	public HotelResponseBean getUser(String userType) {
		List<UserBean> userList = userService.getUser(userType);
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("User List Found...");
		hotelResponseBean.setUserList(userList);
		return hotelResponseBean;
	}

	@GetMapping("/getHotel")
	public HotelResponseBean getHotel(String hotelLocation) {
		List<HotelBean> hotelList = userService.getHotel(hotelLocation);
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("Hotel List Found...");
		hotelResponseBean.setHotelList(hotelList);
		return hotelResponseBean;
	}

	@PostMapping("/changePassword")
	public HotelResponseBean changePassword(@RequestBody UserBean userBean, @RequestParam String newPassword) {
		userService.changePassword(userBean, newPassword);
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("Password Changed Successfully");
		return hotelResponseBean;
	}

	@DeleteMapping(path = "/deleteUser")
	public HotelResponseBean deleteUser(int userId) {

		userService.deleteUser(userId);
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		hotelResponseBean.setMessage("sucess");
		hotelResponseBean.setDescription("User deleted Suceesfully");
		return hotelResponseBean;
	}// end of deleteUser
}
