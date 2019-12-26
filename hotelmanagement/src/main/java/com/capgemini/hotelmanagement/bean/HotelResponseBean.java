package com.capgemini.hotelmanagement.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelResponseBean {

	private int statuscode;
	private String message;
	private String description;
	private UserBean userBean;
	private List<UserBean> userList;
	public List<HotelBean> hotelList;
	public List<RoomBean> roomList;
	public List<BookingBean> bookingList;
	public Double totalBill; 
	
	public List<HotelBean> getHotelList() {
		return hotelList;
	}
	public void setHotelList(List<HotelBean> hotelList) {
		this.hotelList = hotelList;
	}
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public List<UserBean> getUserList() {
		return userList;
	}
	public void setUserList(List<UserBean> userList) {
		this.userList = userList;
	}
	public List<RoomBean> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<RoomBean> roomList) {
		this.roomList = roomList;
	}
	public List<BookingBean> getBookingList() {
		return bookingList;
	}
	public void setBookingList(List<BookingBean> bookingList) {
		this.bookingList = bookingList;
	}
	public Double getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(Double totalBill) {
		this.totalBill = totalBill;
	}
	
	

}