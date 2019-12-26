package com.capgemini.hotelmanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.hotelmanagement.bean.HotelResponseBean;
import com.capgemini.hotelmanagement.bean.RoomBean;
import com.capgemini.hotelmanagement.service.RoomService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@PostMapping("/addRoom")
	public HotelResponseBean registerUser(@RequestBody RoomBean roomBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		boolean isAdded = roomService.addRoom(roomBean);

		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("Room Added Successfully");
		return hotelResponseBean;
	}

	@GetMapping(path = "/getAllRoom")
	public HotelResponseBean getAllRoom(int hotelId) {
		List<RoomBean> roomList = roomService.getAllRooms(hotelId);
		HotelResponseBean hotelResponse = new HotelResponseBean();
		hotelResponse.setStatuscode(201);
		hotelResponse.setMessage("Success");
		hotelResponse.setDescription("Rooms in Hotel fetched Successfuly");
		hotelResponse.setRoomList(roomList);
		return hotelResponse;
	}

	@PostMapping(path = "/updateRoom")
	public HotelResponseBean updateRoom(@RequestBody RoomBean roomBean) {
		roomService.updateRoom(roomBean);
		HotelResponseBean hotelResponse = new HotelResponseBean();
		hotelResponse.setStatuscode(201);
		hotelResponse.setMessage("Success");
		hotelResponse.setDescription("Room information updated successfully");
		return hotelResponse;
	}

	@DeleteMapping("/deleteRoom")
	public HotelResponseBean deleteHotel(@RequestBody RoomBean roomBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		int roomId = roomBean.getRoomId();
		roomService.deleteRoom(roomId);
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("Room Deleted Successfully");
		return hotelResponseBean;
	}
}
