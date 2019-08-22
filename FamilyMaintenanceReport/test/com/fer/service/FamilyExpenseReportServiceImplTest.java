package com.fer.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.fer.bean.PersonalInfo;
import com.fer.bean.User;
import com.fer.dbutil.HibernateUtil;

public class FamilyExpenseReportServiceImplTest {
	
	static Logger logger = Logger.getLogger(FamilyExpenseReportServiceImplTest.class);

	@Test
	public void testLogin() {
		String userName = "manideep2008@yahoo.co.in";
		String password = "Manideep786";
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		PersonalInfo personalInfo = null;
		logger.info("FamilyExpenseReportServiceImpl.login() Start");
		try {
			
			Query query = session.createSQLQuery("SELECT u.*, a.* FROM USER u LEFT JOIN address a ON u.userName='"
					+ userName + "' AND u.password='" + password + "' AND u.userId=a.userId").addEntity(User.class);
			List<User> list = query.list();

			Iterator<User> userIterator = list.iterator();
			while (userIterator.hasNext()) {
				User user = userIterator.next();
				if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
					personalInfo = new PersonalInfo();
					personalInfo.setUser(user);
					break;
				}
			}

		} catch (Exception e) {
			logger.error("SQLException: " + e);

		} finally {
			session.close();
		}

		logger.debug("Personal Info: " + personalInfo);
		logger.info("FamilyExpenseReportServiceImpl.login() End");
		
		assertNotNull(personalInfo);
		
	}
	
	@Test
	public void testLoginFail() {
		String userName = "manideep2008@yahoo.co.in";
		String password = "Manideep785";
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		PersonalInfo personalInfo = null;
		logger.info("FamilyExpenseReportServiceImpl.login() Start");
		try {
			
			Query query = session.createSQLQuery("SELECT u.*, a.* FROM USER u LEFT JOIN address a ON u.userName='"
					+ userName + "' AND u.password='" + password + "' AND u.userId=a.userId").addEntity(User.class);
			List<User> list = query.list();

			Iterator<User> userIterator = list.iterator();
			while (userIterator.hasNext()) {
				User user = userIterator.next();
				if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
					personalInfo = new PersonalInfo();
					personalInfo.setUser(user);
					break;
				}
			}

		} catch (Exception e) {
			logger.error("SQLException: " + e);

		} finally {
			session.close();
		}

		logger.debug("Personal Info: " + personalInfo);
		logger.info("FamilyExpenseReportServiceImpl.login() End");
		
		assertNull(personalInfo);
		
	}
	
}
