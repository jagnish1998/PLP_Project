package com.capgemini.hotelmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.capgemini.hotelmanagement.bean.UserBean;
import com.capgemini.hotelmanagement.exception.HotelManagementException;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Override
	public boolean addUser(UserBean userBean) throws HotelManagementException {
		boolean isAdded =  false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(userBean);
			entityTransaction.commit();
			isAdded = true;
		}catch (Exception e) {
			throw new HotelManagementException("Email Already Exist Please  try with diffrent Email");
		}
		return isAdded; 
	}

	
	@Override
	public UserBean login(String email, String password) throws HotelManagementException {
		UserBean userBean =  null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			String jpql = "from UserBean where email = :email and password = :password";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("email", email);
			query.setParameter("password", password);
			userBean = (UserBean)query.getSingleResult();
		}catch (Exception e) {
			throw new HotelManagementException("Invalid Email and Password");
		}
		return userBean;
	}


	@Override
	public List<UserBean> getAllUser() throws HotelManagementException {
		List<UserBean> userList = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			String jpql = "from UserBean ";
			Query query = entityManager.createQuery(jpql);
			userList = (List<UserBean>)query.getSingleResult();
		}catch (Exception e) {
			throw new HotelManagementException("Unable to get all User");
		}
		return userList;
	}


	@Override
	public List<UserBean> getuser(String type) throws HotelManagementException {
		List<UserBean> userList = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			String jpql = "from UserBean where userType = :userType";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userType", type);
			userList = (List<UserBean>)query.getSingleResult();
		}catch (Exception e) {
			throw new HotelManagementException("No User of type"+type+"is present");
		}
		return userList;
	}
}
