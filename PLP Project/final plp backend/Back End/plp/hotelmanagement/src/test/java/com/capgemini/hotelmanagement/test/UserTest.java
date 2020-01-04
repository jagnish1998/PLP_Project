package com.capgemini.hotelmanagement.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.hotelmanagement.bean.HotelBean;
import com.capgemini.hotelmanagement.bean.UserBean;
import com.capgemini.hotelmanagement.dao.HotelDaoImpl;
import com.capgemini.hotelmanagement.dao.UserDaoImpl;
import com.capgemini.hotelmanagement.exception.HotelManagementException;



@SpringBootTest
public class UserTest {

	@Autowired
	UserDaoImpl userDaoImpl;
	@Autowired
	HotelDaoImpl hotelDaoImpl;
	
	//valid
	
	  @Test public void testAddUser() { UserBean userBean = new UserBean();
	  userBean.setEmail("abc@gmail.com"); userBean.setAddress("pune");
	  userBean.setNationality("Indian"); userBean.setPassword("Abcd@123");
	  userBean.setPhoneNo("8857863105"); userBean.setUserName("Rohit");
	  userBean.setUserType("user"); boolean user = userDaoImpl.addUser(userBean);
	  assertEquals(true, user); }
	 
	//invalid
	@Test
	public void testAddUserInvalid() {
		UserBean userBean = new UserBean();
		userBean.setEmail("roh@gmail.com");		//invalid email
		userBean.setAddress("pune");
		userBean.setHotelId(101);
		userBean.setNationality("Indian");
		userBean.setPassword("Rohit@123");
		userBean.setPhoneNo("8857863105");
		userBean.setUserName("Rohit");
		userBean.setUserType("user"); 
		assertThrows(HotelManagementException.class, ()-> {
			userDaoImpl.addUser(userBean);
		});
	}
	//valid
	@Test
	public void testLogin() {
		String email = "snehal@gmail.com";
		String password = "Snehal@17";
		UserBean user = userDaoImpl.login(email, password);
		assertEquals(1, user.getUserId());

	}
	//invalid
		@Test
		public void testLoginInvalid() {
			String email = "shital@gmail";
			String password = "Shital@21";		//invalid email
			assertThrows(HotelManagementException.class, ()-> {
				userDaoImpl.login(email,password);
			});

		}
	//valid
	@Test
	public void testUpdateUserProfile() {
		UserBean userBean = new UserBean();
		userBean.setUserId(6);
		userBean.setEmail("diya@gmail.com");
		userBean.setAddress("Bangalore");
		userBean.setHotelId(102);
		userBean.setNationality("Indian");
		userBean.setPassword("Divya@08");
		userBean.setPhoneNo("8600279745");
		userBean.setUserName("divya chavan");
		userBean.setUserType("user");
		boolean user = userDaoImpl.updateUserProfile(userBean);
		assertEquals(true, user);
	}
	//invalid
	@Test
	public void testUpdateUserProfileInvalid()  {
		UserBean userBean = new UserBean();
		userBean.setUserId(100);
		userBean.setEmail("divya@gmail.com");
		userBean.setAddress("Bangalore");
		userBean.setHotelId(6);
		userBean.setNationality("Indian");
		userBean.setPassword("Divya@123");
		userBean.setPhoneNo("8600278978");
		userBean.setUserName("divya");
		userBean.setUserType("user");
		assertThrows(HotelManagementException.class, ()-> {
			userDaoImpl.updateUserProfile(userBean);
		});
	}

	//valid
	@Test
	public void testGetUser() throws HotelManagementException {
	
		 UserBean userBean =new UserBean();
		 userBean.setUserType("user");
		 userBean.setUserId(1);
		 assertEquals(1,userBean.getUserId());
		
	}
	
	@Test
	public void testChangePassword() {

		UserBean userBean = new UserBean();
		userBean.setUserId(10);
		userBean.setEmail("divya@gmail.com");
		userBean.setAddress("Bangalore");
		userBean.setNationality("Indian");
		userBean.setPhoneNo("8600279745");
		userBean.setUserName("divya");
		userBean.setUserType("user");
		userBean.setPassword("Divya@123");
		String newPassword="Diya@123";
		boolean isChanged=userDaoImpl.changePassword(userBean,newPassword);
		assertEquals(true,isChanged);
	}
}
