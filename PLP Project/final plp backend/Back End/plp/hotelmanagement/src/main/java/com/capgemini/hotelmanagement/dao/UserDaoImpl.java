package com.capgemini.hotelmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.hotelmanagement.bean.HotelBean;
import com.capgemini.hotelmanagement.bean.UserBean;
import com.capgemini.hotelmanagement.exception.HotelManagementException;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	public boolean addUser(UserBean userBean) {
		boolean isAdded = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(userBean);
			entityTransaction.commit();
			isAdded = true;
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new HotelManagementException("Email Already Exist Please  try with diffrent Email !!");
		}
		return isAdded;
	}

	@Override
	public UserBean login(String email, String password) {
		UserBean userBean = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			String jpql = "from UserBean where email = :email";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("email", email);
			userBean = (UserBean) query.getSingleResult();
		} catch (Exception e) {
			throw new HotelManagementException("Email not Registered Please registered First !!");
		}
		if (!(userBean.getPassword().equals(password))) {
			throw new HotelManagementException("Invalid Password Please Enter Correct Password");
		}
		return userBean;
	}

	@Override
	public boolean updateUserProfile(UserBean userBean) {

		boolean isUpdate = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		UserBean userBean2 = entityManager.find(UserBean.class, userBean.getUserId());
		if (userBean2 != null) {
			if (userBean.getUserName() != null) {
				userBean2.setUserName((userBean.getUserName()));
			}
			if (userBean.getAddress() != null) {
				userBean2.setAddress(userBean.getAddress());
			}
			if (userBean.getPhoneNo() != null) {
				userBean2.setPhoneNo(userBean.getPhoneNo());
			}
		} else {
			throw new HotelManagementException("Invalid UserId ");
		}
		try {
			entityTransaction.begin();
			entityManager.persist(userBean2);
			entityTransaction.commit();
			isUpdate = true;
		} catch (Exception e) {
			throw new HotelManagementException("Unable to Update User");
		}
		return isUpdate;
	}

	@Override
	public List<UserBean> getAllUser() throws HotelManagementException {
		List<UserBean> userList = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			String jpql = "from UserBean";
			Query query = entityManager.createQuery(jpql);
			userList = (List<UserBean>) query.getResultList();
			if (userList == null || userList.isEmpty()) {
				throw new HotelManagementException("No User Present Please Add User First");
			}
		} catch (Exception e) {
			throw new HotelManagementException("No User Present Please Add User First");
		}
		return userList;
	}

	@Override
	public List<UserBean> getUser(String type) throws HotelManagementException {
		List<UserBean> userList = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			String jpql = "from UserBean where userType = :userType";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userType", type);
			userList = (List<UserBean>) query.getResultList();
			if (userList == null || userList.isEmpty()) {
				throw new HotelManagementException("No User of type" + type + "is present");
			}
		} catch (Exception e) {
			throw new HotelManagementException("No User of type" + type + "is present");

		}
		return userList;
	}

	@Override
	public List<HotelBean> getHotel(String hotelLocation) throws HotelManagementException {
		List<HotelBean> hotelList = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			String jpql = "from HotelBean where hotelLocation=:hotelLocation";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("hotelLocation", hotelLocation);
			hotelList = (List<HotelBean>) query.getResultList();
			if (hotelList == null || hotelList.isEmpty()) {
				throw new HotelManagementException("Unable to get all HotalLocation");
			}
		} catch (Exception e) {
			throw new HotelManagementException("Unable to get all HotalLocation");
		}
		return hotelList;
	}

	@Override
	public boolean changePassword(UserBean userBean, String newPassword) {
		boolean isUpdated = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			String password = userBean.getPassword();
			int id = userBean.getUserId();
			
			UserBean userBean2 = entityManager.find(UserBean.class,id);
			
			if(userBean2.getPassword().equals(password)) {
				entityTransaction.begin();
				userBean2.setPassword(newPassword);
				entityManager.persist(userBean2);
				entityTransaction.commit();
				isUpdated = true;
			}else {
				throw new HotelManagementException("Please Enter Correct Old Password");
			}
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new HotelManagementException("Please Enter Correct Old Password");
		}
		return isUpdated;
	}

	@Override
	public UserBean viewProfile(String email) throws HotelManagementException {
		UserBean userBean = null;
		try {

			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction entityTransaction = entityManager.getTransaction();
			String jpql = "from UserBean where email=:email";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("email", email);
			userBean = (UserBean) query.getSingleResult();
			return userBean;
		} catch (Exception e) {
			throw new HotelManagementException("User Email not Present");
		}
	}

	@Override
	public boolean deleteUser(int userId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean isDeleted = false;

		try {
			entityTransaction.begin();
			UserBean userBean = entityManager.find(UserBean.class, userId);
			entityManager.remove(userBean);
			entityTransaction.commit();
			isDeleted = true;
			entityManager.close();
		} catch (Exception e) {
			throw new HotelManagementException("Unable to delete User !!");
		}
		return isDeleted;
	}

	@Override
	public boolean deleteemployeeHotel(int hotelId) {
		boolean isChanged = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		String jpql = "Delete From UserBean Where hotelId =:hotelId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("hotelId", hotelId);
		try {
			entityTransaction.begin();
			int result = query.executeUpdate();
			entityTransaction.commit();
			isChanged = true;
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new HotelManagementException("Unable To Delete Hotel");
		}
		return isChanged;
	}

}
