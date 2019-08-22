package com.rs.fer.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rs.fer.pojo.Address;
import com.rs.fer.pojo.Expense;
import com.rs.fer.pojo.User;
import com.rs.fer.util.DBUtil;

public class ServiceImpl implements Service {

	Connection connection = null;
	PreparedStatement statement = null;

	@Override
	public boolean register(User user) {
		int insertedRecords = 0;
		try {
			connection = DBUtil.getConnection();
			String insertQuery = "insert into user(firstname, middlename, lastname, username, password, email, mobile) values(?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(insertQuery);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getMiddleName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getUserName());
			statement.setString(5, user.getPassword());
			statement.setString(6, user.getEmail());
			statement.setString(7, user.getMobile());
			insertedRecords = statement.executeUpdate();
			System.out.println(insertedRecords + " Record is Inserted");
			System.out.println(user.getFirstName() + ", " + user.getMiddleName() + ", " + user.getLastName() + ", "
					+ user.getUserName() + ", " + user.getPassword() + ", " + user.getEmail() + ", "
					+ user.getMobile());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}

		return insertedRecords > 0;
	}

	@Override
	public int login(String username, String password) {
		try {
			connection = DBUtil.getConnection();
			String fetchRecords = "select * from user where username=? and password=?";
			statement = connection.prepareStatement(fetchRecords);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			System.out.println(username + ", " + password);
			while (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return 0;
	}

	@Override
	public boolean addExpense(Expense expense) {
		int addExpenseRecords = 0;
		try {
			connection = DBUtil.getConnection();
			String query = "insert into expense(expensetype,date,price,no_of_items,total,by_whom,userid) values(?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, expense.getExpenseType());
			statement.setString(2, expense.getDate());
			statement.setFloat(3, expense.getPrice());
			statement.setInt(4, expense.getNo_of_items());
			statement.setFloat(5, expense.getTotal());
			statement.setString(6, expense.getBy_whom());
			statement.setInt(7,expense.getUserid());

			addExpenseRecords = statement.executeUpdate();
			System.out.println(addExpenseRecords + " Expense is Added");
			System.out.println(expense.getExpenseType() + ", " + expense.getDate() + ", " + expense.getPrice() + ", "
					+ expense.getNo_of_items() + ", " + expense.getTotal() + ", " + expense.getBy_whom() + ", "
					+ expense.getUserid());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return addExpenseRecords > 0;
	}

	@Override
	public boolean editExpense(Expense expense) {
		int expensesEdited = 0;
		try {
			connection = DBUtil.getConnection();
			String inputQuery = "update expense set expensetype=?, date=?, price=?, no_of_items=?, total=?, by_whom=? where id=?";
			statement = connection.prepareStatement(inputQuery);
			statement.setString(1, expense.getExpenseType());
			statement.setString(2, expense.getDate());
			statement.setFloat(3, expense.getPrice());
			statement.setInt(4, expense.getNo_of_items());
			statement.setFloat(5, expense.getTotal());
			statement.setString(6, expense.getBy_whom());
			statement.setInt(7, expense.getId());

			expensesEdited = statement.executeUpdate();
			System.out.println(expense.getExpenseType() + ", " + expense.getDate() + ", " + expense.getPrice() + ", "
					+ expense.getNo_of_items() + ", " + expense.getTotal() + ", " + expense.getBy_whom() + ", "
					+ expense.getId());
			System.out.println(expensesEdited + " Expense Record is Edited");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}

		return expensesEdited > 0;
	}

	@Override
	public boolean deleteExpense(int expenseId) {
		int expensesDeleted = 0;
		try {
			connection = DBUtil.getConnection();
			String inputQuery = "delete from expense where id=?";
			statement = connection.prepareStatement(inputQuery);
			statement.setInt(1, expenseId);
			expensesDeleted = statement.executeUpdate();
			System.out.println(expenseId + " is Deleted");
			System.out.println(expensesDeleted + " Expense Record is Deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return expensesDeleted > 0;
	}

	@Override
	public Expense getExpense(int expenseId) {
		Expense expense = null;
		try {
			connection = DBUtil.getConnection();
			String inputQuery = "select * from expense where id = ?";
			statement = connection.prepareStatement(inputQuery);
			statement.setInt(1, expenseId);

			ResultSet resultSet = statement.executeQuery();
			System.out.println(resultSet + " Expense Report is Fetched");

			while (resultSet.next()) {
				expense = new Expense();
				expense.setId(resultSet.getInt("id"));
				expense.setExpenseType(resultSet.getString("expensetype"));
				expense.setDate(resultSet.getString("date"));
				expense.setPrice(resultSet.getFloat("price"));
				expense.setNo_of_items(resultSet.getInt("no_of_items"));
				expense.setTotal(resultSet.getFloat("total"));
				expense.setBy_whom(resultSet.getString("by_whom"));
				expense.setUserid(resultSet.getInt("userid"));
				System.out.println(resultSet.getString("expensetype") + ", " + resultSet.getString("date") + ", "
						+ resultSet.getString("price") + ", " + resultSet.getString("no_of_items") + ", "
						+ resultSet.getString("total") + ", " + resultSet.getString("by_whom") + ", "
						+ resultSet.getInt("userid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return expense;
	}

	@Override
	public List<Expense> getExpenses(int userId) {
		List<Expense> expenses = new ArrayList<Expense>();

		try {
			connection = DBUtil.getConnection();
			String inputQuery = "select * from expense where userId = ?";
			statement = connection.prepareStatement(inputQuery);
			statement.setInt(1, userId);

			ResultSet resultSet = statement.executeQuery();
			System.out.println(resultSet + " Expense Report is Fetched");

			Expense expense = null;
			while (resultSet.next()) {
				expense = new Expense();
				expense.setId(resultSet.getInt("id"));
				expense.setExpenseType(resultSet.getString("expensetype"));
				expense.setDate(resultSet.getString("date"));
				expense.setPrice(resultSet.getFloat("price"));
				expense.setNo_of_items(resultSet.getInt("no_of_items"));
				expense.setTotal(resultSet.getFloat("total"));
				expense.setBy_whom(resultSet.getString("by_whom"));
				expense.setUserid(resultSet.getInt("userId"));

				expenses.add(expense);
				System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("expensetype") + ", "
						+ resultSet.getString("date") + ", " + resultSet.getString("no_of_items") + ", "
						+ resultSet.getString("price") + ", " + resultSet.getString("by_whom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return expenses;
	}

	@Override
	public boolean resetPassword(int userid, String currentPaswword, String newPassword) {
		int resetPassword = 0;
		try {
			connection = DBUtil.getConnection();
			String inputQuery = "update user set password=? where id=? and password=?";
			statement = connection.prepareStatement(inputQuery);
			statement.setString(1, newPassword);
			statement.setInt(2, userid);
			statement.setString(3, currentPaswword);

			resetPassword = statement.executeUpdate();
			System.out.println(resetPassword + " Record is Updated");
			System.out.println(newPassword + ", " + userid + ", " + currentPaswword);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return resetPassword > 0;
	}

	@Override
	public User getPersonal(int userId) {
		User user = new User();
		try {
			connection = DBUtil.getConnection();
			String query = "select u.*,a.* from user u left join address a on u.id = a.userid where userid=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setMiddleName(resultSet.getString("middlename"));
				user.setLastName(resultSet.getString("lastname"));
				user.setMobile(resultSet.getString("mobile"));
				user.setEmail(resultSet.getString("email"));
				user.setUserName(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));

				Address address = new Address();
				address.setId(resultSet.getInt(9));
				address.setUserid(resultSet.getInt("userid"));
				address.setLineOne(resultSet.getString("lineOne"));
				address.setLineTwo(resultSet.getString("lineTwo"));
				address.setStreet(resultSet.getString("street"));
				address.setCity(resultSet.getString("city"));
				address.setState(resultSet.getString("state"));
				address.setZip(resultSet.getString("zip"));

				user.setAddress(address);
				System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("firstname") + ", "
						+ resultSet.getString("middlename") + ", " + resultSet.getString("lastname") + ", "
						+ resultSet.getString("mobile") + ", " + resultSet.getString("email") + ", "
						+ resultSet.getString("username") + ", " + resultSet.getString("password") + ", "
						+ resultSet.getInt("userid") + ", " + resultSet.getString("lineOne") + ", "
						+ resultSet.getString("lineTwo") + ", " + resultSet.getString("street") + ", "
						+ resultSet.getString("city") + ", " + resultSet.getString("state") + ", "
						+ resultSet.getString("zip"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return user;
	}

	@Override
	public boolean Updatepersonal(User user) {
		int recordCount = 0;
		try {
			connection = DBUtil.getConnection();
			String query = "update user  set firstname = ? , middlename = ? , lastname = ? , mobile = ? , email = ? where id = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getMiddleName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getMobile());
			statement.setString(5, user.getEmail());
			statement.setInt(6, user.getId());
			recordCount = statement.executeUpdate();
			System.out.println(recordCount + " record is updated");

			Address address = user.getAddress();
			String input = "";
			if (recordCount > 0) {
				if (address.getId() != 0) {
					input = "update address set lineOne = ? , lineTwo = ? ,street = ? ,city = ? ,state = ? ,zip = ? where userid = ?";
				} else {
					input = "insert into address (lineOne , lineTwo , street , city , state , zip , userid) values(?,?,?,?,?,?,?)";
				}
				statement = connection.prepareStatement(input);
				statement.setString(1, address.getLineOne());
				statement.setString(2, address.getLineTwo());
				statement.setString(3, address.getStreet());
				statement.setString(4, address.getCity());
				statement.setString(5, address.getState());
				statement.setString(6, address.getZip());
				statement.setInt(7, address.getUserid());
				int registeredCount = statement.executeUpdate();
				System.out.println(registeredCount + "address record updated");
				System.out.println(user.getId() + ", " + user.getFirstName() + ", " + user.getMiddleName() + ", "
						+ user.getLastName() + ", " + user.getMobile() + ", " + user.getEmail() + ", "
						+ user.getUserName() + ", " + user.getPassword() + ", " + address.getId() + ", "
						+ address.getLineOne() + ", " + address.getLineTwo() + ", " + address.getStreet() + ", "
						+ address.getCity() + ", " + address.getState() + ", " + address.getZip() + ", "
						+ address.getUserid());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}

		return recordCount > 0;
	}

	@Override
	public List<Expense> expenseReport(int userid, String expenseType, String fromDate, String toDate) {
		List<Expense> expenseReport = new ArrayList<Expense>();
		try {
			connection = DBUtil.getConnection();
			String inputQuery = "select * from expense where userid = ? and expensetype = ? and date between ? and ?";
			statement = connection.prepareStatement(inputQuery);
			statement.setInt(1, userid);
			statement.setString(2, expenseType);
			statement.setString(3, fromDate);
			statement.setString(4, toDate);

			ResultSet resultSet = statement.executeQuery();
			System.out.println(resultSet + " Record is Fetched");
			while (resultSet.next()) {
				Expense expense = new Expense();
				expense.setId(resultSet.getInt("id"));
				expense.setExpenseType(resultSet.getString("expensetype"));
				expense.setDate(resultSet.getString("date"));
				expense.setNo_of_items(resultSet.getInt("no_of_items"));
				expense.setPrice(resultSet.getInt("price"));
				expense.setBy_whom(resultSet.getString("by_whom"));
				expense.setUserid(resultSet.getInt("userid"));
				System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("expensetype") + ", "
						+ resultSet.getString("date") + ", " + resultSet.getString("no_of_items") + ", "
						+ resultSet.getString("price") + ", " + resultSet.getString("by_whom") + ", "
						+ resultSet.getInt("userid"));
				expenseReport.add(expense);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return expenseReport;

	}

}
