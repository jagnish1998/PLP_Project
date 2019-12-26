package com.capgemini.hotelmanagement.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.hotelmanagement.bean.BookingBean;
import com.capgemini.hotelmanagement.bean.HotelResponseBean;
import com.capgemini.hotelmanagement.service.BookingService;
import com.capgemini.hotelmanagement.service.RoomService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookingController {
	@Autowired
	BookingService bookingService;

	@Autowired
	RoomService roomService;

//to calaculate Total Number of Days
	public static int noOfDays(LocalDate checkInDate, LocalDate checkOutDate) {
		return (int) (ChronoUnit.DAYS.between(checkInDate, checkOutDate));
	}

//to get Sysstem Date
	public static java.sql.Date todysDate() {
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		System.out.println(date);
		return date;
	}

	@PostMapping("/bookRoom")
	public HotelResponseBean BookRoom(@RequestBody BookingBean bookingBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();

		if (!(bookingBean.getCheckinDate().isBefore(bookingBean.getCheckoutDate()))) {
			hotelResponseBean.setStatuscode(400);
			hotelResponseBean.setMessage("Fail");
			hotelResponseBean.setDescription("Please Enter checkout date greater then checkin date !!");
		} else {
			int days = noOfDays(bookingBean.getCheckinDate(), bookingBean.getCheckoutDate());
			bookingBean.setDays(days);
			bookingService.bookRoom(bookingBean);
			boolean isChanged = roomService.changeStatus(bookingBean.getRoomId());
			if (isChanged) {
				hotelResponseBean.setStatuscode(200);
				hotelResponseBean.setMessage("Success");
				hotelResponseBean.setDescription("Booking done Successfully");
			}
		}
		return hotelResponseBean;
	}

	@PostMapping("/makeStatusAvailable")
	public HotelResponseBean makeStatusAvailable(@RequestParam int roomId) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		roomService.statusAvailable(roomId);
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("Room Status changed To Available Successfully");
		return hotelResponseBean;

	}

	@GetMapping("/bookingDetails")
	// public HotelResponseBean dayDetails(@RequestParam LocalDate checkinDate)
	public HotelResponseBean dayDetails(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkinDate) {
		List<BookingBean> bookingList = bookingService.bookingDetails(checkinDate);
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("Booking  List Found...");
		hotelResponseBean.setBookingList(bookingList);
		return hotelResponseBean;
	}

	@GetMapping("/bookingDetailsForHotel")
	public HotelResponseBean dayDetailsFor(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkinDate, int hotelId) {
		List<BookingBean> bookingList = bookingService.bookingDetailsForHotel(checkinDate, hotelId);
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("Success");
		hotelResponseBean.setDescription("Booking  List Found...");
		hotelResponseBean.setBookingList(bookingList);
		return hotelResponseBean;
	}

	@PostMapping("/payment")
	public HotelResponseBean payment(@RequestBody BookingBean bookingBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		if (!(bookingBean.getCheckinDate().isBefore(bookingBean.getCheckoutDate()))) {
			hotelResponseBean.setStatuscode(400);
			hotelResponseBean.setMessage("Fail");
			hotelResponseBean.setDescription("Please Enter checkout date greater then checkin date !!");
		} else {
			int days = noOfDays(bookingBean.getCheckinDate(), bookingBean.getCheckoutDate());
			double price = roomService.roomPrice(bookingBean.getRoomId());
			double totalPrice = days * price;
			hotelResponseBean.setStatuscode(200);
			hotelResponseBean.setMessage("Success");
			hotelResponseBean.setDescription("Payemt Getting Sucessfullly");
			hotelResponseBean.setTotalBill(totalPrice);
		}
		return hotelResponseBean;
	}

	@DeleteMapping(path = "/cancelBooking")
	public HotelResponseBean deleteUser(@RequestBody BookingBean bookingBean) {
		int bookingId = bookingBean.getBookingId();
		int roomId = bookingBean.getRoomId();
		bookingService.cancelBooking(bookingId);
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		boolean isChanged = roomService.statusAvailable(roomId);
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage("sucess");
		hotelResponseBean.setDescription("Booking Cancel  Suceesfully");
		return hotelResponseBean;
	}// end of cancel Booking

}
