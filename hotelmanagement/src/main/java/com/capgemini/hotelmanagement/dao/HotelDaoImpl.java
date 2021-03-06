package com.capgemini.hotelmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.hotelmanagement.bean.HotelBean;
import com.capgemini.hotelmanagement.bean.UserBean;
import com.capgemini.hotelmanagement.exception.HotelManagementException;

@Repository
public class HotelDaoImpl implements HotelDao {
	
	@PersistenceUnit
	 private EntityManagerFactory entityManagerFactory ;

	@Override
	public boolean addHotel(HotelBean hotelBean){
		boolean isAdded =  false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(hotelBean);
			entityTransaction.commit();
			isAdded = true;
		}catch (Exception e) {
			throw new HotelManagementException("Unable to add Hotel");
		}
		return isAdded; 	
	}

	@Override
	public boolean deleteHotel(int hotelId) {
		boolean isDeleted=false;
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction  entityTransaction=entityManager.getTransaction();
		try {
			entityTransaction.begin();
			HotelBean hotelBean=entityManager.find(HotelBean.class, hotelId);
			entityManager.remove(hotelBean);
			entityTransaction.commit();
			isDeleted=true;
			
		} catch (Exception e) {
			throw new HotelManagementException("Hotel Not Present !!!");
		}
		return isDeleted;
	}

	@Override
	public boolean updateHotel(HotelBean hotelBean) {
            boolean isUpdate=false;
            EntityManager entityManager=entityManagerFactory.createEntityManager();
    		EntityTransaction  entityTransaction=entityManager.getTransaction();
            HotelBean hotelBean2=entityManager.find(HotelBean.class, hotelBean.getHotelId());
            if(hotelBean2!=null) {
            	if (hotelBean.getHotelLocation() != null) {
    				hotelBean2.setHotelLocation((hotelBean.getHotelLocation()));
    			}
            	if(hotelBean.getHotelName()!=null) {
            		hotelBean2.setHotelName(hotelBean.getHotelName());
            	}
            	if(hotelBean.getContactNo()!=null) {
            		hotelBean2.setContactNo(hotelBean.getContactNo());
            	}
            	
            }else {
            	throw new HotelManagementException("No Hotel is Present");
            }
             try {
            	 entityTransaction.begin();
            	 entityManager.persist(hotelBean2);
            	 entityTransaction.commit();
            	 isUpdate=true;
				
			} catch (Exception e) {
				throw new HotelManagementException("Unable to Update Hotel");
			}
		return isUpdate;
	}

	@Override
	public List<HotelBean> getAllHotels() {
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction  entityTransaction=entityManager.getTransaction();
		String jpql = "from HotelBean";
		Query query = entityManager.createQuery(jpql);
		List<HotelBean> hotelList = null;
		try {
			entityTransaction.begin();
			hotelList = query.getResultList();
            entityTransaction.commit();
		} catch (Exception e) {
			throw new HotelManagementException("No Hotel Present ");
		}

		return hotelList;
	}

	@Override
	public List<UserBean> getEmployee(int hotelId) {
		
		List<UserBean> employeeList = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			String jpql = "from UserBean where hotelId =:hotelId and userType ='employee'";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("hotelId", hotelId);
			employeeList = (List<UserBean>) query.getResultList();
		} catch (Exception e) {
			throw new HotelManagementException("No Employee is Present for this Hotel");
		}
		return employeeList;
	}
}
