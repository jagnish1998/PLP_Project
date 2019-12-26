package com.capgemini.hotelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hotelmanagement.bean.RoomBean;
import com.capgemini.hotelmanagement.dao.RoomDao;
import com.capgemini.hotelmanagement.exception.HotelManagementException;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;

	@Override
	public List<RoomBean> getAllRooms(int hotelId) {
		if (hotelId < 0) {
			throw new HotelManagementException("Please Enter some HotelId hotelId cannot be Null");
		}
			return roomDao.getAllRooms(hotelId);
	}

	@Override
	public boolean updateRoom(RoomBean roomBean) {
		if (roomBean == null) {
			throw new HotelManagementException("Please Enter Some value It Canot be Null");
		}
			return roomDao.updateRoom(roomBean);
	}

	@Override
	public boolean deleteRoom(int roomId) {
		if (roomId < 0) {
			throw new HotelManagementException("Please Enter Some value It Canot be Null");
		}
			return roomDao.deleteRoom(roomId);
	}

	@Override
	public boolean addRoom(RoomBean roomBean) {
		if (roomBean == null) {
			throw new HotelManagementException("Please Enter Some value It Canot be Null");
		}
			return roomDao.addRoom(roomBean);
	}

	@Override
	public boolean changeStatus(int roomId) {
			throw new HotelManagementException("Please Enter Some value It Canot be Null");
	}
	@Override
	public boolean statusAvailable(int roomId) {
			return roomDao.statusAvailable(roomId);
	}

	@Override
	public double roomPrice(int roomId) {
			return roomDao.roomPrice(roomId);
	}

//	@Override
//	public boolean isRoomNoExists(int roomNo, int hotelId) {
//		if(roomNo<0) {
//			return false;
//		}
//		try {
//			return roomDao.isRoomNoExists(roomNo, hotelId);
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		return false;
//	}

}
