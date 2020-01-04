package com.capgemini.hotelmanagement.dao;

import java.util.List;

import com.capgemini.hotelmanagement.bean.HotelBean;
import com.capgemini.hotelmanagement.bean.UserBean;


public interface HotelDao {

	public boolean addHotel(HotelBean hotelBean) ;

	public boolean deleteHotel(int hotelId) ;

	public boolean updateHotel(HotelBean hotelBean) ;

	public List<HotelBean> getAllHotels() ;

	public List<UserBean> getEmployee(int hotelId) ;
	
	public HotelBean getHotel(int hotelId);
}
