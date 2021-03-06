package com.capgemini.hotelmanagement.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.hotelmanagement.bean.BookingBean;


public interface BookingDao {
	public boolean bookRoom(BookingBean bookingBean);

	public List<BookingBean> bookingDetails(LocalDate checkinDate);

	public List<BookingBean> bookingDetailsForHotel(LocalDate checkinDate, int hotelId);
	
	public List<BookingBean> bookingDetailsForParticularHotel(int hotelId);
	
	public List<BookingBean> bookingHistory(int userId);

	public boolean cancelBooking(int bookingId);
}
