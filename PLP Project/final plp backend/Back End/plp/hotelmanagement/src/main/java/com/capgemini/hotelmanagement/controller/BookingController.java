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
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class BookingController {
	@Autowired
	BookingService bookingService;

	@Autowired
	RoomService roomService;

	String success = "Success";

	// to calculate Total Number of Days
	public static int noOfDays(LocalDate checkInDate, LocalDate checkOutDate) {
		return (int) (ChronoUnit.DAYS.between(checkInDate, checkOutDate));
	}

	@PostMapping("/bookRoom")
	public HotelResponseBean bookRoom(@RequestBody BookingBean bookingBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		if (bookingBean.getCheckinDate().equals(bookingBean.getCheckoutDate())) {
			int days = 1;
			bookingBean.setDays(days);
			bookingService.bookRoom(bookingBean);
			roomService.changeStatus(bookingBean.getRoomId());
			hotelResponseBean.setStatuscode(200);
			hotelResponseBean.setMessage(success);
			hotelResponseBean.setDescription("Booking done Successfully");
		} else {
			if (!(bookingBean.getCheckinDate().isBefore(bookingBean.getCheckoutDate()))) {
				hotelResponseBean.setStatuscode(400);
				hotelResponseBean.setMessage("Fail");
				hotelResponseBean.setDescription("Please Enter checkout date greater then checkin date !!");
			} else {
				int days = noOfDays(bookingBean.getCheckinDate(), bookingBean.getCheckoutDate());
				bookingBean.setDays(days);
				bookingService.bookRoom(bookingBean);
				roomService.changeStatus(bookingBean.getRoomId());
				hotelResponseBean.setStatuscode(200);
				hotelResponseBean.setMessage(success);
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
		hotelResponseBean.setMessage(success);
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
		hotelResponseBean.setMessage(success);
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
		hotelResponseBean.setMessage(success);
		hotelResponseBean.setDescription("Booking  List Found...");
		hotelResponseBean.setBookingList(bookingList);
		return hotelResponseBean;
	}// end of bookingDetails For hotel
	
	@GetMapping("/bookingDetailsForParticularHotel")
	public HotelResponseBean bookingDetailsFor(int hotelId) {
		List<BookingBean> bookingList = bookingService.bookingDetailsForParticularHotel(hotelId);
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage(success);
		hotelResponseBean.setDescription("Booking  List Found...");
		hotelResponseBean.setBookingList(bookingList);
		return hotelResponseBean;
	}// end of bookingDetails For hotel

	@PostMapping("/payment")
	public HotelResponseBean payment(@RequestBody BookingBean bookingBean) {
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		if (bookingBean.getCheckinDate().equals(bookingBean.getCheckoutDate())) {
			int days = 1;
			double price = roomService.roomPrice(bookingBean.getRoomId());
			double totalPrice = days * price;
			hotelResponseBean.setStatuscode(200);
			hotelResponseBean.setMessage("Success");
			hotelResponseBean.setDescription("Total Amount to be paid is found");
			hotelResponseBean.setTotalBill(totalPrice);
		} else {
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
		}
		return hotelResponseBean;
	}// end of payment

	@GetMapping(path = "/bookingHistory")
	public HotelResponseBean bookingHistory(int userId) {
		List<BookingBean> bookingList = bookingService.bookingHistory(userId);
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage(success);
		hotelResponseBean.setDescription("BookingList Found SuccessFul");
		hotelResponseBean.setBookingList(bookingList);
		return hotelResponseBean;
	}// end of booking History

	@DeleteMapping(path = "/cancelBooking")
	public HotelResponseBean deleteUser(@RequestBody BookingBean bookingBean) {
		int bookingId = bookingBean.getBookingId();
		int roomId = bookingBean.getRoomId();
		bookingService.cancelBooking(bookingId);
		HotelResponseBean hotelResponseBean = new HotelResponseBean();
		roomService.statusAvailable(roomId);
		hotelResponseBean.setStatuscode(200);
		hotelResponseBean.setMessage(success);
		hotelResponseBean.setDescription("Booking Cancel  Suceesfully");
		return hotelResponseBean;
	}// end of cancel Booking
}
