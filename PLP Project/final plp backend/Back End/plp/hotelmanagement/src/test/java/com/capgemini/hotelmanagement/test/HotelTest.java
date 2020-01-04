package com.capgemini.hotelmanagement.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.hotelmanagement.bean.HotelBean;
import com.capgemini.hotelmanagement.dao.HotelDaoImpl;
import com.capgemini.hotelmanagement.exception.HotelManagementException;

@SpringBootTest
public class HotelTest {

	@Autowired
	HotelDaoImpl hotelDaoImpl;

	// valid
	@Test
	public void testAddHotels() {

		HotelBean hotelBean = new HotelBean();
		hotelBean.setHotelLocation("Pune");
		hotelBean.setHotelName("Novotel");

		boolean add = hotelDaoImpl.addHotel(hotelBean);
		assertEquals(true, add);
	}

//	invalid
	@Test
	public void testAddHotelsInvalid() {

		HotelBean hotelBean = new HotelBean();
		hotelBean.setHotelId(1);
		hotelBean.setHotelLocation("Pune");
		hotelBean.setHotelName("Novotel"); // hotel is already present

		assertThrows(HotelManagementException.class, () -> {
			hotelDaoImpl.addHotel(hotelBean);
		});

	}

	// valid
	/*
	 * @Test public void testDeleteHotel() { boolean test =
	 * hotelDaoImpl.deleteHotel(1); assertEquals(true,test); }
	 */

	// invalid
	@Test
	public void testDeleteHotelInvalid() {
		assertThrows(HotelManagementException.class, () -> {
			hotelDaoImpl.deleteHotel(1);
		});

	}

	// valid
	@Test
	public void testUpdateHotel() {
		HotelBean hotelBean = new HotelBean();

		hotelBean.setHotelId(14);
		hotelBean.setHotelLocation("Hyderabad");
		hotelBean.setHotelName("Manohar");

		boolean update = hotelDaoImpl.updateHotel(hotelBean);
		assertEquals(true, update);
	}

	@Test
	public void testUpdateHotelInvalid() {
		HotelBean hotelBean = new HotelBean();

		hotelBean.setHotelId(15);
		hotelBean.setHotelLocation("Hyderabad");
		hotelBean.setHotelName("Manohar");

		assertThrows(HotelManagementException.class, () -> {
			hotelDaoImpl.updateHotel(hotelBean);
		});
	}

	@Test
	public void testGetHotel() {

		HotelBean hotelBean2 = hotelDaoImpl.getHotel(7);
		assertEquals(7, hotelBean2.getHotelId());
	}

}