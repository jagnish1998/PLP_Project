package com.capgemini.hotelmanagement.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.hotelmanagement.bean.BookingBean;
import com.capgemini.hotelmanagement.exception.HotelManagementException;

@Repository
public class BookingDaoImpl implements BookingDao {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	public boolean bookRoom(BookingBean bookingBean) {
		boolean isAdded = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(bookingBean);
			entityTransaction.commit();
			isAdded = true;
		} catch (Exception e) {
			throw new HotelManagementException("Booking Is Not Done");
		}
		return isAdded;
	}

	@Override
	public List<BookingBean> bookingDetails(LocalDate checkinDate) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from BookingBean where checkinDate=:checkinDate";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("checkinDate", checkinDate);
		List<BookingBean> bookingList = null;
		try {
			bookingList = query.getResultList();
			if (bookingList == null || bookingList.isEmpty()) {
				throw new HotelManagementException("You have No booking On This Date");
			}
		} catch (Exception e) {
			throw new HotelManagementException("You have No booking On This Date");
		}
		return bookingList;
	}

	@Override
	public List<BookingBean> bookingDetailsForHotel(LocalDate checkinDate, int hotelId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from BookingBean where checkinDate=:checkinDate and hotelId=: hotelId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("checkinDate", checkinDate);
		query.setParameter("hotelId", hotelId);
		List<BookingBean> bookingList = null;
		try {
			bookingList = query.getResultList();
			if (bookingList == null || bookingList.isEmpty()) {
				throw new HotelManagementException("No Booking for This Date");
			}
		} catch (Exception e) {
			throw new HotelManagementException("No Booking for This Date");
		}
		return bookingList;
	}

	@Override
	public boolean cancelBooking(int bookingId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean isDeleted = false;
		try {
			entityTransaction.begin();
			BookingBean bookingBean = entityManager.find(BookingBean.class, bookingId);
			entityManager.remove(bookingBean);
			entityTransaction.commit();
			isDeleted = true;
		} catch (Exception e) {
			throw new HotelManagementException("Unable to cancel the booking Please Try Later");
		}
		entityManager.close();
		return isDeleted;
	}

	@Override
	public List<BookingBean> bookingHistory(int userId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from BookingBean where userId=:userId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("userId", userId);
		List<BookingBean> bookingList = null;
		try {
			bookingList = query.getResultList();
			if (bookingList == null || bookingList.isEmpty()) {
				throw new HotelManagementException("You have no previous booking");
			}
		} catch (Exception e) {
			throw new HotelManagementException("You have no previous booking");
		}
		return bookingList;
	}

	@Override
	public List<BookingBean> bookingDetailsForParticularHotel(int hotelId) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from BookingBean where hotelId=: hotelId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("hotelId", hotelId);
		List<BookingBean> bookingList = null;
		try {
			bookingList = query.getResultList();
			if (bookingList == null || bookingList.isEmpty()) {
				throw new HotelManagementException("No Bookings For Hotel");
			}
		} catch (Exception e) {
			throw new HotelManagementException("No Bookings For Hotel");
		}
		return bookingList;
	}
}
