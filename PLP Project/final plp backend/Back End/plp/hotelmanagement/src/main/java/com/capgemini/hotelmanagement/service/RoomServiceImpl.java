package com.capgemini.hotelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hotelmanagement.bean.RoomBean;
import com.capgemini.hotelmanagement.dao.RoomDao;
import com.capgemini.hotelmanagement.exception.HotelManagementException;
import com.capgemini.hotelmanagement.validation.HotelValidation;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private HotelValidation hotelValidation;

	@Override
	public List<RoomBean> getAllRooms(int hotelId) {
		return roomDao.getAllRooms(hotelId);
	}

	@Override
	public boolean updateRoom(RoomBean roomBean) {
		return roomDao.updateRoom(roomBean);
	}

	@Override
	public boolean deleteRoom(int roomId) {
		return roomDao.deleteRoom(roomId);
	}

	@Override
	public boolean addRoom(RoomBean roomBean) {
		String roomRent = Double.toString(roomBean.getRoomRent());
		if (hotelValidation.roomRentValidation(roomRent)) {
			return roomDao.addRoom(roomBean);
		} else {
		
			throw new HotelManagementException("Please Enter Valid room Rent");
		}
	}

	@Override
	public boolean changeStatus(int roomId) {
		return roomDao.changeStatus(roomId);
	}

	@Override
	public boolean statusAvailable(int roomId) {
		return roomDao.statusAvailable(roomId);
	}

	@Override
	public double roomPrice(int roomId) {
		return roomDao.roomPrice(roomId);
	}

	@Override
	public boolean deleteRoomUsingHotel(int hotelId) {
		return roomDao.deleteRoomUsingHotel(hotelId);
	}

}
