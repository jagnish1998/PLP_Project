package com.capgemini.hotelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.hotelmanagement.bean.HotelBean;
import com.capgemini.hotelmanagement.bean.UserBean;
import com.capgemini.hotelmanagement.dao.HotelDao;
import com.capgemini.hotelmanagement.exception.HotelManagementException;
import com.capgemini.hotelmanagement.validation.HotelValidation;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelDao hotelDao;
	@Autowired
	HotelValidation hotelValidation;

	@Override
	public boolean addHotel(HotelBean hotelBean) {
		
		String hotelLocation=hotelBean.getHotelLocation();
		
			if(hotelValidation.hotelLocationValidation(hotelLocation)) {
				
			return hotelDao.addHotel(hotelBean);
			}
			else {
				throw new HotelManagementException("Enter Characters Only For HotelLocation");
			}
		 
	}

	@Override
	public boolean deleteHotel(int hotelId) {
		return hotelDao.deleteHotel(hotelId);
	}

	@Override
	public boolean updateHotel(HotelBean hotelBean) {
	
		return hotelDao.updateHotel(hotelBean);
	}

	@Override
	public List<HotelBean> getAllHotels() {
		return hotelDao.getAllHotels();
	}

	@Override
	public List<UserBean> getEmployee(int hotelId) {
		return hotelDao.getEmployee(hotelId);
	}

	@Override
	public HotelBean getHotel(int hotelId) {

		return hotelDao.getHotel(hotelId);
	}

}
