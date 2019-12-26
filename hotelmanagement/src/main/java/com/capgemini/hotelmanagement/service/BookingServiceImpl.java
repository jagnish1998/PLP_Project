package com.capgemini.hotelmanagement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hotelmanagement.bean.BookingBean;
import com.capgemini.hotelmanagement.dao.BookingDao;
import com.capgemini.hotelmanagement.exception.HotelManagementException;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingDao bookingDao;
	
	@Override
	public boolean bookRoom(BookingBean bookingBean) {
		
			return bookingDao.bookRoom(bookingBean);
	}

	@Override
	public List<BookingBean> bookingDetails(LocalDate checkinDate) {
			return bookingDao.bookingDetails(checkinDate);
	}

	@Override
	public List<BookingBean> bookingDetailsForHotel(LocalDate checkinDate, int hotelId) {
			return bookingDao.bookingDetailsForHotel(checkinDate, hotelId);
	}

	@Override
	public boolean cancelBooking(int bookingId) {
		return bookingDao.cancelBooking(bookingId);
	}
}