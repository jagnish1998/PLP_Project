package com.capgemini.hotelmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.capgemini.hotelmanagement.bean.RoomBean;
import com.capgemini.hotelmanagement.exception.HotelManagementException;

@Repository
public class RoomDaoImpl implements RoomDao {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	public List<RoomBean> getAllRooms(int hotelId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<RoomBean> roomList = null;
		try {
			String jpql = "from RoomBean where hotelId= :hotelId and roomStatus='available'";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("hotelId", hotelId);
			roomList = query.getResultList();
		} catch (Exception e) {
			throw new HotelManagementException("Sorry! Room are not Available Please Check Other Hotel ");
		}
		return roomList;
	}

	@Override
	public boolean updateRoom(RoomBean newRoomBean)  {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		boolean isUpdated = false;
		RoomBean roomBean = entityManager.find(RoomBean.class, newRoomBean.getRoomId());
		if (roomBean != null) {
			String capacity = newRoomBean.getRoomCapacity();
			if (capacity != null) {
				roomBean.setRoomCapacity(capacity);
			}

			String status = newRoomBean.getRoomStatus();
			if (status != null) {
				roomBean.setRoomStatus(status);
			}

			String type = newRoomBean.getRoomType();
			if (type != null) {
				roomBean.getRoomType();
			}

			double rent = newRoomBean.getRoomRent();
			if (rent != 0) {
				roomBean.setRoomRent(rent);
			}

			int roomNo = newRoomBean.getRoomNo();
			if (roomNo > 0) {
				roomBean.setRoomNo(roomNo);
			}

			try {
				transaction.begin();
				entityManager.persist(roomBean);
				isUpdated = true;
				transaction.commit();
			} catch (Exception e) {
				throw new HotelManagementException("Unable to Update the room Information");
			}
		}
		return isUpdated;
	}

	@Override
	public boolean deleteRoom(int roomId) {

		boolean isDeleted = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			RoomBean roomaBean = entityManager.find(RoomBean.class, roomId);
			entityManager.remove(roomaBean);
			entityTransaction.commit();
			isDeleted = true;

		} catch (Exception e) {
			throw new HotelManagementException("RoomId "+roomId+" Not Present !!!");
		}
		return isDeleted;
	}

	@Override
	public boolean addRoom(RoomBean roomBean)  {

		boolean isAdded = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(roomBean);
			entityTransaction.commit();
			isAdded = true;
		} catch (Exception e) {
			throw new HotelManagementException("Unable to add roomBean Please");
		}
		return isAdded;
	}

	@Override
	public boolean changeStatus(int roomId)  {
		boolean isChanged = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		String jpql = "update RoomBean set roomStatus = 'unavailable' where roomId =:roomId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("roomId", roomId);
		try {
			entityTransaction.begin();
			query.executeUpdate();
			entityTransaction.commit();
			isChanged = true;
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new HotelManagementException("Unable to change status of Room");
		}
		return isChanged;
	}

	@Override
	public boolean statusAvailable(int roomId) {

		boolean isChanged = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		String jpql = "update RoomBean set roomStatus = 'available' where roomId =:roomId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("roomId", roomId);
		try {
			entityTransaction.begin();
			query.executeUpdate();
			entityTransaction.commit();
			isChanged = true;
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new HotelManagementException("Unable to change status of romm");
		}
		return isChanged;
	}

	@Override
	public double roomPrice(int roomId)  {
		double roomRent = 0;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "select roomRent From RoomBean where roomId =:roomId";
		try {
			Query query = entityManager.createQuery(jpql);
			query.setParameter("roomId", roomId);
			roomRent = (double) query.getSingleResult();
		} catch (Exception e) {
			throw new HotelManagementException("Invalid Room Id");
		}
		return roomRent;
	}

//	@Override
//	public boolean isRoomNoExists(int roomNo,int hotelId) throws HotelManagementException {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction entityTransaction = entityManager.getTransaction();
//		boolean isPresent=false;
//		try {
//			String jpql = "from RoomBean where roomNo= :roomNo and hotelId= :hotelId";
//			Query query = entityManager.createQuery(jpql);
//			query.setParameter("roomNo", roomNo);
//			query.setParameter("hotelId", hotelId);
//			entityTransaction.commit();
//			isPresent=true;
//			
//		} catch (Exception e) {
//			//throw new HotelManagementException("Room Already Present In Hotel !!!");
//			return isPresent;
//		}
//		return isPresent;
//	}

}
