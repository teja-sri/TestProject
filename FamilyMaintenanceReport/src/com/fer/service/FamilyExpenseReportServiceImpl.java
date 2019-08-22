package com.fer.service;

//import com.fer.servlet.ResetPasswordServlet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.fer.bean.Address;
import com.fer.bean.Balance;
import com.fer.bean.Expense;
import com.fer.bean.ExpenseGroup;
import com.fer.bean.ExpenseName;
import com.fer.bean.PersonalInfo;
import com.fer.bean.User;
import com.fer.dbutil.DBUtil;
import com.fer.dbutil.HibernateUtil;

public class FamilyExpenseReportServiceImpl implements FamilyExpenseReportServiceInterface {
	static Logger logger = Logger.getLogger(FamilyExpenseReportServiceImpl.class);

	Map<String, ExpenseGroup> expenseGroups = new HashMap<String, ExpenseGroup>();
	Map<String, ExpenseName> expenseNames = new HashMap<String, ExpenseName>();
	Map<String, Balance> balances = new HashMap<String, Balance>();

	@Override
	public boolean registration(User user) {
		/*
		 * Connection connection = (Connection) DBUtil.getConnection(); int
		 * numberOfRecords = 0;
		 * logger.info("FamilyExpenseReportServiceImpl.registration() Start"); try {
		 * logger.info("Registration: " + user); Statement stmt =
		 * connection.createStatement(); String s =
		 * "insert into user(firstName,lastName,email,userName,password) values('" +
		 * user.getFirstName() + "','" + user.getLastName() + "','" + user.getEmail() +
		 * "','" + user.getUserName() + "','" + user.getPassword() + "')";
		 * numberOfRecords = stmt.executeUpdate(s); System.out.println(numberOfRecords +
		 * " records inserted");
		 * 
		 * } catch (SQLException e) { logger.error("SQLException: " + e); } finally {
		 * DBUtil.closeConnection(connection); }
		 * logger.info("Number of Updated Records: " + numberOfRecords);
		 * logger.info("FamilyExpenseReportServiceImpl.registration() End"); return
		 * (numberOfRecords > 0);
		 */

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			Transaction transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();

		} catch (Exception e) {
			System.err.println("Error creating User :" + e.getMessage());
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

		return true;

	}

	@Override
	public PersonalInfo login(String userName, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		PersonalInfo personalInfo = null;
		logger.info("FamilyExpenseReportServiceImpl.login() Start");
		try {
			/*
			 * Criteria criteria = session.createCriteria(User.class); Criterion criterion1
			 * = Restrictions.eq("userName", userName); Criterion criterion2 =
			 * Restrictions.eq("password", password); criteria.add(criterion1);
			 * criteria.add(criterion2); List list = criteria.list(); Iterator iterator =
			 * list.iterator(); while(iterator.hasNext()){ User user = (User)
			 * iterator.next();
			 * 
			 * user.setId(user.getId()); user.setFirstName(user.getFirstName());
			 * user.setLastName(user.getLastName()); user.setEmail(user.getEmail());
			 * user.setUserName(user.getUserName()); user.setPassword(user.getPassword());
			 * user.setMiddleName(user.getMiddleName()); user.setPhone(user.getPhone());
			 * user.setImagePath(user.getImagePath()); personalInfo.setUser(user); int id =
			 * user.getId();
			 * 
			 * Criteria criteria1 = session.createCriteria(Address.class); Criterion
			 * criterion3 = Restrictions.eq("id", id); criteria1.add(criterion3); List
			 * addressList = criteria1.list();
			 * 
			 * Iterator iterator2 = list.iterator(); while(iterator2.hasNext()){ Address
			 * address = (Address) iterator2.next(); address.getId(); address.getUserId();
			 * address.getStreetLine(); address.getTown(); address.getCity();
			 * address.getState(); address.getCountry();
			 * 
			 * personalInfo.setAddress(address);
			 * 
			 * }
			 */
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
			System.out.println(session);
			session.close();
		}

		logger.debug("Personal Info: " + personalInfo);
		logger.info("FamilyExpenseReportServiceImpl.login() End");
		return personalInfo;

	}

	@Override
	public boolean resetPassword(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		logger.info("FamilyExpenseReportServiceImpl.resetPassword() start");
		try {
			Transaction transaction = session.beginTransaction();
			Query query = session
					.createSQLQuery("update user set password='" + password + "' where userName='" + username + "'");
			int numberOfRows = query.executeUpdate();
			transaction.commit();
			return numberOfRows > 0;
		} catch (Exception e) {
			logger.error("SQLException: " + e);
		} finally {
			session.close();
		}

		logger.info("FamilyExpenseReportServiceImpl.resetPassword() End");
		return false;
	}

	@Override
	public boolean addExpense(Expense expense) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		logger.info("FamilyExpenseReportServiceImpl.addExpense() start");
		try {

			Transaction transaction = session.beginTransaction();
			session.save(expense);
			transaction.commit();

			session.close();
			balances = getBalances();
			Balance balanceObject = new Balance();

			if (balances != null && !balances.isEmpty()) {
				Balance balance = (balances.containsKey(expense.getDebitedFrom()))
						? balances.get(expense.getDebitedFrom())
						: new Balance();
				balanceObject.setActionType("C");
				balanceObject.setAmount(balance.getAmount() - Float.parseFloat(expense.getTotal() + ""));
				balanceObject.setDate(new Date());
				balanceObject.setType(expense.getDebitedFrom());
				balanceObject.setUserId(expense.getUserId());
			}

			session = HibernateUtil.getSessionFactory().openSession();

			transaction = session.beginTransaction();
			session.save(balanceObject);
			transaction.commit();

			balances.clear();
			return true;

		} catch (Exception e) {
			logger.error("SQLException: " + e);
		} finally {
			session.close();
		}

		logger.info("addExpense: " + expense);
		logger.info("FamilyExpenseReportServiceImpl.addExpense() start");
		return false;
	}

	@Override
	public boolean editExpense(Expense expense) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
			int id = expense.getId();
			Expense expense1 = (Expense) session.get(Expense.class, id);
			session.clear();
			session.update(expense);

			transaction.commit();

			session.close();
			balances = getBalances();
			Balance balanceObject = new Balance();
			
			session = HibernateUtil.getSessionFactory().openSession();

			transaction = session.beginTransaction();

			if (balances != null && !balances.isEmpty()) {
				Balance balance = (balances.containsKey(expense.getDebitedFrom()))
						? balances.get(expense.getDebitedFrom())
						: new Balance();
				balanceObject.setActionType("C");
				if (expense.getTotal() > expense1.getTotal()) {
					int difference = (int) (expense1.getTotal() - expense.getTotal());
					balanceObject.setAmount(balance.getAmount() - difference);
				} else {

					int difference = (int) (expense.getTotal() - expense1.getTotal());
					balanceObject.setAmount(balance.getAmount() + difference);

				}

				balanceObject.setDate(new Date());
				balanceObject.setType(expense.getDebitedFrom());
				balanceObject.setUserId(expense.getUserId());
				balanceObject.setType(expense.getDebitedFrom());
			}

			
			session.save(balanceObject);
			transaction.commit();

			balances.clear();

			return true;
		} catch (Exception e) {
			logger.error("SQLException: " + e);
		} finally {
			session.close();
		}

		logger.info("editExpense: " + expense);
		logger.info("FamilyExpenseReportServiceImpl.addExpense() End");
		return false;

	}

	@Override
	public boolean deleteExpense(Expense expense) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		logger.info("FamilyExpenseReportServiceImpl.deleteExpense() start");
		try {
			Transaction transaction = session.beginTransaction();
			System.out.println("This is for delete......");
			
			Object deleteObject = session.load(Expense.class, expense.getId());
			Object deleteObject1 = session.load(Expense.class, expense.getId());
						
			balances = getBalances();
			Balance balanceObject = new Balance();

			if (balances != null && !balances.isEmpty()) {
				Balance balance = (balances.containsKey(expense.getDebitedFrom()))
						? balances.get(expense.getDebitedFrom())
						: new Balance();
				balanceObject.setActionType("C");
				balanceObject.setAmount(balance.getAmount() + Float.parseFloat(expense.getTotal() + ""));
				balanceObject.setDate(new Date());
				balanceObject.setType(expense.getDebitedFrom());
				balanceObject.setUserId(expense.getUserId());
			}
			
			session.delete(deleteObject);
			transaction.commit();
			
			session = HibernateUtil.getSessionFactory().openSession();

			transaction = session.beginTransaction();
			session.save(balanceObject);
			transaction.commit();

			balances.clear();
			
			return true;
		} catch (Exception e) {
			logger.error("SQLException: " + e);
		} finally {
			session.close();
		}

		logger.info("deleteExpense: " + expense);
		logger.info("FamilyExpenseReportServiceImpl.deleteExpense() End");
		return false;
	}

	@Override
	public boolean updatePersonalInfo(User user) {
		Connection connection = DBUtil.getConnection();
		logger.info("FamilyExpenseReportServiceImpl.updatePersonalInfo() start");
		int numberOfRows = 0;
		try {
			Statement stmt = connection.createStatement();
			String update = "update user set middlename='" + user.getMiddleName() + "' where firstname='"
					+ user.getFirstName() + "' and lastname='" + user.getLastName() + "'";
			numberOfRows = stmt.executeUpdate(update);
			System.out.println(numberOfRows + " records updated");
		} catch (SQLException e) {
			logger.error("SQLException: " + e);
		} finally {
			DBUtil.closeConnection(connection);
		}
		logger.info("Number of records updated: " + numberOfRows);
		logger.info("updatePersonalInfo: " + user);
		logger.info("FamilyExpenseReportServiceImpl.updatePersonalInfo() End");
		return (numberOfRows > 0);
	}

	public boolean updatePersonalInfo(User user, Address address) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		logger.info("FamilyExpenseReportServiceImpl.updatePersonalInfo() start");
		int numberOfRows = 0;
		try {
			Transaction transaction = session.beginTransaction();

			Query updateRecord = session.createSQLQuery("update user set middleName='" + user.getMiddleName()
					+ "', firstName='" + user.getFirstName() + "', lastName='" + user.getLastName() + "', email='"
					+ user.getEmail() + "', phone='" + user.getPhone() + "' where userId = " + user.getId() + "")
					.addEntity(User.class);

			numberOfRows = updateRecord.executeUpdate();

			if (numberOfRows > 0 && updateAddressInfo(address, transaction)) {
				transaction.commit();
			} else {
				transaction.rollback();
			}
			return true;

		} catch (Exception e) {
			logger.error("SQLException: " + e);
		} finally {
			session.close();
		}

		logger.info("Number of records inserted: " + numberOfRows);
		logger.info("updatePersonalInformation: " + user);
		logger.info("FamilyExpenseReportServiceImpl.updatePersonalInfo() End");
		return false;
	}

	@Override
	public boolean updateContactInfo(User user) {
		Connection connection = DBUtil.getConnection();
		int numberOfRows = 0;
		try {
			Statement statement = connection.createStatement();
			String updateRecord = "update user set phone=" + user.getPhone() + " where email='" + user.getEmail() + "'";
			numberOfRows = statement.executeUpdate(updateRecord);
			System.out.println(numberOfRows + " records updated");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return (numberOfRows > 0);
	}

	@Override
	public boolean updateAddressInfo(Address address, Transaction transaction) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		logger.info("FamilyExpenseReportServiceImpl.updateAddress() start");
		int numberOfRows = 0;
		try {
			transaction = session.beginTransaction();
			if (address.getId() == 0) {
				session.save(address);
			} else {
				session.update(address);
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			logger.error("SQLException: " + e);
		} finally {
			session.close();
		}

		logger.info("updateAddress: " + address);
		logger.info("FamilyExpenseReportServiceImpl.addExpense() End");
		return false;
	}

	@Override
	public ArrayList<Expense> expenseReport(String type, String name, String byWhom, String fromDate, String toDate,
			int numberOfRecords, int numberOfRecordsPerPage, int pageIndex) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		logger.info("FamilyExpenseReportServiceImpl.expenseReport() start");
		List<Expense> expenses = new ArrayList<Expense>();
		Expense expense = null;
		try {

			Criteria criteria = session.createCriteria(Expense.class);

			Criterion criterion4 = Restrictions.between("date", fromDate, toDate);
			criteria.add(criterion4);

			if (!"ALL".equals(type)) {
				Criterion criterion1 = Restrictions.eq("type", type);
				criteria.add(criterion1);
			}
			if (!"ALL".equals(name)) {
				Criterion criterion2 = Restrictions.eq("name", name);
				criteria.add(criterion2);
			}
			if (!"ALL".equals(byWhom)) {
				Criterion criterion3 = Restrictions.eq("byWhom", byWhom);
				criteria.add(criterion3);
			}

			criteria.addOrder(Order.desc("date"));

			criteria.setFirstResult((pageIndex * numberOfRecordsPerPage) - numberOfRecordsPerPage);
			criteria.setMaxResults(numberOfRecordsPerPage);

			expenses = criteria.list();
		} catch (Exception e) {

			logger.error("SQLException: " + e);
		} finally {
			session.close();
		}

		// logger.info("Number of records inserted: "+numberOfRows);
		logger.info("expenseReport: " + expense);
		logger.info("FamilyExpenseReportServiceImpl.expenseReport() End");
		return (ArrayList<Expense>) expenses;
	}

	@Override
	public List<Expense> getExpense(Expense expense) {
		Connection connection = DBUtil.getConnection();
		logger.info("FamilyExpenseReportServiceImpl.getExpense() start");
		List<Expense> expenses = new ArrayList<Expense>();
		try {
			Statement statement = connection.createStatement();
			String query = "select * from expense";
			ResultSet resultset = statement.executeQuery(query);

			// Expense expense = null;
			while (resultset.next()) {
				// expense = new Expense();
				expense.setId(resultset.getInt(1));
				expense.setType(resultset.getString(2));
				expense.setDate(resultset.getString(3));
				expense.setPrice(resultset.getDouble(4));
				expense.setNumberOfItems(resultset.getInt(5));
				expense.setTotal(resultset.getDouble(6));
				expense.setByWhom(resultset.getString(7));

				expenses.add(expense);

			}

		} catch (SQLException e) {
			logger.error("SQLException: " + e);
		} finally {
			DBUtil.closeConnection(connection);
		}
		logger.info("gettExpense: " + expense);
		logger.info("FamilyExpenseReportServiceImpl.getExpense() End");
		return expenses;
	}

	@Override
	public Expense generateExpenses(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		logger.info("FamilyExpenseReportServiceImpl.generateExpense() start");
		try {
			int id1 = Integer.parseInt(id);
			Object selectObject = session.load(Expense.class, id1);
			Expense expense = (Expense) selectObject;

			expense.getId();
			expense.getType();
			expense.getPrice();
			expense.getNumberOfItems();
			expense.getTotal();
			expense.getByWhom();

			return expense;
		} catch (Exception e) {
			logger.error("SQLException: " + e);
		} finally {
			session.close();
		}

		logger.info("FamilyExpenseReportServiceImpl. generateExpense() End");
		return null;
	}

	@Override
	public boolean uploadPicture(String userName, String imagePath) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		int numberOfRows = 0;
		try {
			User user = new User();
			Transaction transaction = session.beginTransaction();
			Query query = session.createSQLQuery(
					"update user set imagePath = '" + imagePath + "' where userName='" + userName + "'");
			numberOfRows = query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return (numberOfRows > 0);
	}

	@Override
	public List<Expense> getExpenses() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		List<Expense> expenses = new ArrayList<Expense>();

		try {
			Query query = session.createQuery("from Expense expense");
			expenses = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return expenses;
	}

	@Override
	public boolean saveExpenseGroup(String expenseGroupName) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		boolean saveExpenseGroupFlag = false;

		try {
			ExpenseGroup expenseGroup = new ExpenseGroup();
			expenseGroup.setName(expenseGroupName);

			Transaction transaction = session.beginTransaction();
			session.save(expenseGroup);

			transaction.commit();

			saveExpenseGroupFlag = true;

			expenseGroups.clear();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return saveExpenseGroupFlag;
	}

	@Override
	public boolean saveExpenseName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		boolean saveExpenseNameFlag = false;

		try {
			ExpenseName expenseName = new ExpenseName();
			expenseName.setName(name);

			Transaction transaction = session.beginTransaction();
			session.save(expenseName);

			transaction.commit();

			saveExpenseNameFlag = true;

			expenseNames.clear();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return saveExpenseNameFlag;
	}

	@Override
	public Map<String, ExpenseGroup> getExpenseGroups() {
		logger.info("FamilyExpenseReportServiceImpl.getExpenseGroups() start");

		if (expenseGroups != null && !expenseGroups.isEmpty()) {
			return expenseGroups;
		}

		List<ExpenseGroup> expenseGroupList = fetchExpenseGroups();
		if (expenseGroupList != null && expenseGroupList != null) {
			for (ExpenseGroup expenseGroup : expenseGroupList) {
				expenseGroups.put(expenseGroup.getId() + "", expenseGroup);
			}
		}

		logger.info("FamilyExpenseReportServiceImpl.getExpenseGroups() End");
		return expenseGroups;
	}

	private List<ExpenseGroup> fetchExpenseGroups() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		List<ExpenseGroup> expenseGroups = new ArrayList<ExpenseGroup>();
		try {

			Criteria criteria = session.createCriteria(ExpenseGroup.class);
			expenseGroups = criteria.list();
		} catch (Exception e) {
			logger.error("SQLException: " + e);
		} finally {
			session.close();
		}

		return expenseGroups;
	}

	@Override
	public Map<String, ExpenseName> getExpenseNames() {
		logger.info("FamilyExpenseReportServiceImpl.getExpenseNames() start");

		if (expenseNames != null && !expenseNames.isEmpty()) {
			return expenseNames;
		}

		List<ExpenseName> expenseNameList = fetchExpenseNames();
		if (expenseNameList != null && expenseNameList != null) {
			for (ExpenseName expenseName : expenseNameList) {
				expenseNames.put(expenseName.getId() + "", expenseName);
			}
		}

		logger.info("FamilyExpenseReportServiceImpl.getExpenseNames() End");
		return expenseNames;
	}

	public List<ExpenseName> fetchExpenseNames() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		List<ExpenseName> expenseNames = new ArrayList<ExpenseName>();
		try {

			Criteria criteria = session.createCriteria(ExpenseName.class);
			expenseNames = criteria.list();
		} catch (Exception e) {
			logger.error("SQLException: " + e);
		} finally {
			session.close();
		}

		return expenseNames;
	}

	@Override
	public boolean saveBalance(Balance balance) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		boolean saveBalanceFlag = false;

		try {
			balances = getBalances();
			if (balances != null && balances.containsKey(balance.getType())) {
				Balance balanceObject = balances.get(balance.getType());
				if ("C".equals(balance.getActionType())) {
					balance.setAmount(balanceObject.getAmount() + balance.getAmount());
				} else {
					balance.setAmount(balanceObject.getAmount() - balance.getAmount());
				}
			}

			Transaction transaction = session.beginTransaction();
			session.save(balance);
			transaction.commit();

			saveBalanceFlag = true;

			balances.clear();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return saveBalanceFlag;
	}

	@Override
	public Map<String, Balance> getBalances() {

		if (balances != null && !balances.isEmpty()) {
			return balances;
		}

		return fetchBalances();
	}

	private Map<String, Balance> fetchBalances() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(Balance.class);
			criteria.addOrder(Order.desc("date"));

			Criterion bankCriterion = Restrictions.eq("type", "B");
			criteria.add(bankCriterion);

			List<Balance> bankBalances = criteria.list();
			if (bankBalances != null && !bankBalances.isEmpty()) {
				balances.put("B", bankBalances.get(0));
			}

			criteria = session.createCriteria(Balance.class);
			criteria.addOrder(Order.desc("date"));

			Criterion cashCriterion = Restrictions.eq("type", "C");
			criteria.add(cashCriterion);

			List<Balance> cashBalances = criteria.list();
			if (cashBalances != null && !cashBalances.isEmpty()) {
				balances.put("C", cashBalances.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return balances;
	}

	@Override
	public int totalExpensesCount(String expenseType, String name, String byWhom, String fromDate, String toDate,
			int numberOfRecrods, int numberOfRecordsPerPage, int pageIndex) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		logger.info("FamilyExpenseReportServiceImpl.expenseReport() start");
		try {
			Criteria criteria = session.createCriteria(Expense.class);

			Criterion criterion4 = Restrictions.between("date", fromDate, toDate);
			criteria.add(criterion4);

			if (!"ALL".equals(expenseType)) {
				Criterion criterion1 = Restrictions.eq("type", expenseType);
				criteria.add(criterion1);
			}
			if (!"ALL".equals(byWhom)) {
				Criterion criterion3 = Restrictions.eq("byWhom", byWhom);
				criteria.add(criterion3);
			}

			criteria.setProjection(Projections.rowCount());

			List expensesCount = criteria.list();

			Iterator expensesCountIterator = expensesCount.iterator();

			if (expensesCountIterator.hasNext()) {
				Object object = expensesCountIterator.next();
				return Integer.parseInt(object.toString() + "");
			}
		} catch (Exception e) {
			logger.error("SQLException: " + e);
		} finally {
			session.close();
		}

		// logger.info("Number of records inserted: "+numberOfRows);
		return 0;
	}

	@Override
	public boolean isEmailAvailable(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		logger.info("FamilyExpenseReportServiceImpl.isEmailAvailable() Start");
		try {
			Query query = session.createSQLQuery("SELECT u.* FROM USER u where u.email='" + email + "'")
					.addEntity(User.class);
			List<User> list = query.list();

			Iterator<User> userIterator = list.iterator();
			while (userIterator.hasNext()) {
				User user = userIterator.next();
				if (user.getEmail().equals(email)) {
					return false;
				}
			}

		} catch (Exception e) {
			logger.error("SQLException: " + e);

		} finally {
			System.out.println(session);
			session.close();
		}

		logger.info("FamilyExpenseReportServiceImpl.login() End");
		return true;
	}
}