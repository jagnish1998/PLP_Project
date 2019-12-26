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
import com.capgemini.hotelmanagement.service.HotelService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HotelController {
	@Autowired
	HotelService hotelService;

	@PostMapping("/addHotel")
	public HotelResponseBean registerUser(@RequestBody HotelBean hotelBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		hotelService.addHotel(hotelBean);
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("hotel Added Successfully");
		return hotelResponseBean;
	}

	@DeleteMapping("/deleteHotel")
	public HotelResponseBean deleteHotel(@RequestBody HotelBean hotelBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		int hotelId = hotelBean.getHotelId();
		hotelService.deleteHotel(hotelId);
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("hotel Deleted Successfully");
		return hotelResponseBean;
	}

	@PostMapping("/updateHotel")
	public HotelResponseBean UpdateHotel(@RequestBody HotelBean hotelBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		hotelService.updateHotel(hotelBean);
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("hotel Updated Successfully");
		return hotelResponseBean;
	}

	@GetMapping("/seeAllHotels")
	public HotelResponseBean seeAllHotels() {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		List<HotelBean> hotelList = hotelService.getAllHotels();
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("hotel Updated Successfully");
		hotelResponseBean.setHotelList(hotelList);
		return hotelResponseBean;
	}

	@GetMapping("/getEmployee")
	public HotelResponseBean getUser(@RequestParam int hotelId) {
		List<UserBean> userList = hotelService.getEmployee(hotelId);
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("Employee List Found...");
		hotelResponseBean.setUserList(userList);
		return hotelResponseBean;
	}

}
