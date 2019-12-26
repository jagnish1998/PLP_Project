package com.capgemini.hotelmanagement.service;

import java.util.List;

import com.capgemini.hotelmanagement.bean.HotelBean;
import com.capgemini.hotelmanagement.bean.UserBean;

public interface HotelService {

	public boolean addHotel(HotelBean hotelBean);
	public boolean deleteHotel(int hotelId);
	public boolean updateHotel(HotelBean hotelBean);
    public List<HotelBean> getAllHotels();
    public List<UserBean> getEmployee(int hotelId);
}
