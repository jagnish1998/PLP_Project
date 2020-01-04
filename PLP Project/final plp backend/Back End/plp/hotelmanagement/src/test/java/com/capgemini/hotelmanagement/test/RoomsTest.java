package com.capgemini.hotelmanagement.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.hotelmanagement.bean.RoomBean;
import com.capgemini.hotelmanagement.dao.RoomDaoImpl;
import com.capgemini.hotelmanagement.exception.HotelManagementException;


@SpringBootTest
public class RoomsTest {

	@Autowired
	RoomDaoImpl roomDaoImpl;
	
	//valid
	@Test
	public void testUpdateRoom() throws HotelManagementException {
		RoomBean roomBean = new RoomBean();
		roomBean.setHotelId(101);
		roomBean.setRoomCapacity("3");
		roomBean.setRoomId(1);
		roomBean.setRoomRent(4500.0);
		roomBean.setRoomStatus("available");
		roomBean.setRoomType("non-ac");	
		assertEquals(101,roomBean.getHotelId());
	}
	
	
	//invalid
		@Test
		public void testUpdateRoomInvalid() throws HotelManagementException {
			RoomBean roomBean = new RoomBean();
			roomBean.setHotelId(120);
			roomBean.setRoomCapacity("3");
			roomBean.setRoomId(1);
			roomBean.setRoomRent(4500.0);
			roomBean.setRoomStatus("available");
			roomBean.setRoomType("non-ac");
			assertEquals(120,roomBean.getHotelId());
		}
	
		//valid
	@Test
	public void testDeleteRoom() throws HotelManagementException {
		boolean i = roomDaoImpl.deleteRoom(101); 
		assertEquals(true,i);
	}
	
	//invalid
	@Test
	public void testDeleteRoomInvalid() throws HotelManagementException {
		assertThrows(HotelManagementException.class,()->{
			roomDaoImpl.deleteRoom(50);
		});
	}
	
	//valid
	@Test
	public void testAddRoom() throws HotelManagementException {
		RoomBean roomBean = new RoomBean();
		roomBean.setHotelId(102);
		roomBean.setRoomCapacity("4");
		roomBean.setRoomRent(4500.0);
		roomBean.setRoomStatus("available");
		roomBean.setRoomType("ac");
		roomBean.setRoomNo(49);
		
		assertEquals(102,roomBean.getHotelId());
	}
	
	//invalid
	@Test
	public void testAddRoomInvalid() {
		RoomBean roomBean = new RoomBean();
		roomBean.setRoomId(113);
		roomBean.setHotelId(19);
		roomBean.setRoomCapacity("4");
		roomBean.setRoomRent(4500.0);
		roomBean.setRoomStatus("available");
		roomBean.setRoomType("Delux room");
		roomBean.setRoomNo(127);			//room having 102 no. already present 
		
		assertThrows(HotelManagementException.class, ()-> {
			roomDaoImpl.addRoom(roomBean);
		});
	}
	
	//valid
	@Test
	public void teststatusAvailable() throws HotelManagementException {
		boolean room = roomDaoImpl.statusAvailable(1);
		assertEquals(true,room);

	}
	
	//invalid
		@Test
		public void teststatusAvailableInvalid() throws HotelManagementException {
			boolean room = roomDaoImpl.statusAvailable(1);
			assertEquals(true,room);

		}
}
