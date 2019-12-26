package com.capgemini.hotelmanagement.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.hotelmanagement.bean.BookingBean;
import com.capgemini.hotelmanagement.exception.HotelManagementException;

public interface BookingDao {
	public boolean bookRoom(BookingBean bookingBean);

	public List<BookingBean> bookingDetails(LocalDate checkinDate);

	public List<BookingBean> bookingDetailsForHotel(LocalDate checkinDate, int hotelId);

	public boolean cancelBooking(int bookingId);
}
