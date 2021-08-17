package com.oop.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.oop.exception.AccountException;
import com.oop.model.Transaction;
import com.oop.util.CommonConstants;

import com.oop.util.CommonUtil;
import com.oop.util.DBConnectionUtil;
import com.oop.util.QueryUtil;

public class TransactionService implements ITransactionService {
	
	/** Initialise logger */
	public static final Logger log = Logger.getLogger(TransactionService.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createTransactionTable();
		createRequestTable();
	}

	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing Employees table in the database and
	 * recreate table structure to insert employee entries
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 */
	public static void createTransactionTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			//statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_TABLE_TRANSFER));
			// Create new employees table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_TABLE_TRANSFER));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public static void createRequestTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_Drop_REQUEST_TABLE_TRANSFER));
			// Create new employees table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_REQUEST_TABLE_TRANSFER));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add set of employees for as a batch from the selected employee List
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * 
	 */
	@Override
	public void addTransaction(Transaction transaction) throws AccountException {

		String transactionID = CommonUtil.generateTransferIDs(getTransactionIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_TRANSFER));
			connection.setAutoCommit(false);
			
			//Generate employee IDs
			transaction.setTransactionId(transactionID);
			if(!(transaction.getAccountNo1().equals(transaction.getAccountNo2()))) {
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, transaction.getTransactionId());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, transaction.getAccountNo1());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, transaction.getAccountNo2());
			preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_FOUR, transaction.getAmount());
			// Add employee
			preparedStatement.execute();
			connection.commit();
			}
			else {
				throw new AccountException();
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * Employee details can be retrieved based on the provided employee ID
	 * 
	 * @param employeeID
	 *            - Employee details are filtered based on the ID
	 * 
	 * @see #actionOnEmployee()
	 */
	@Override
	public Transaction getTransactionByID(String transferID) {
		
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		try {
			connection = DBConnectionUtil.getDBConnection();
				/*
				 * Get employee by ID query will be retrieved from
				 * EmployeeQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_TRANSFER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, transferID);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				transaction.setAccountNo1(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				transaction.setAccountNo2(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				transaction.setAmount(resultSet.getFloat(CommonConstants.COLUMN_INDEX_FOUR));
				transactionList.add(transaction);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return transactionList.get(0);

		//return actionOnTransaction(transferID).get(0);
	}
	
	/**
	 * Get all list of employees
	 * 
	 * @return ArrayList<Employee> 
	 * 						- Array of employee list will be return
	 * 
	 * @see #actionOnEmployee()
	 */
	@Override
	public ArrayList<Transaction> getTransactions(String accountNo) {
		
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		try {
			connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_TRANSFERS));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, accountNo);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				transaction.setAccountNo1(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				transaction.setAccountNo2(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				transaction.setAmount(resultSet.getFloat(CommonConstants.COLUMN_INDEX_FOUR));
				transactionList.add(transaction);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return transactionList;
		//return actionOnTransaction(accountNo);
	}

	/**
	 * This method delete an employee based on the provided ID
	 * 
	 * @param employeeID
	 *            - Delete employee according to the filtered employee details
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	@Override
	public void deleteTransaction(String transactionID) {

		// Before deleting check whether employee ID is available
		if (transactionID != null && !transactionID.isEmpty()) {
			/*
			 * Remove employee query will be retrieved from EmployeeQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_DELETE_TRANSFER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, transactionID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	}

	/**
	 * This performs GET employee by ID and Display all employees
	 * 
	 * @param employeeID
	 *            ID of the employee to remove or select from the list

	 * @return ArrayList<Employee> Array of employee list will be return
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 * @see #getEmployees()
	 * @see #getEmployeeByID(String)
	 */
	private ArrayList<Transaction> actionOnTransaction(String transactionID) {

		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching employee it checks whether employee ID is
			 * available
			 */
			if (transactionID != null && !transactionID.isEmpty()) {
				/*
				 * Get employee by ID query will be retrieved from
				 * EmployeeQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_TRANSFER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, transactionID);
			}
			/*
			 * If employee ID is not provided for get employee option it display
			 * all employees
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_TRANSFERS));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, transactionID);
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				transaction.setAccountNo1(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				transaction.setAccountNo2(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				transaction.setAmount(resultSet.getFloat(CommonConstants.COLUMN_INDEX_FOUR));
				transactionList.add(transaction);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return transactionList;
	}

	/**
	 * Get the updated employee
	 * 
	 * @param employeeID
	 *            ID of the employee to remove or select from the list
	 * 
	 * @return return the Employee object
	 * 
	 */
	@Override
	public Transaction updateTransaction(String transactionID, Transaction transaction) throws AccountException {

		/*
		 * Before fetching employee it checks whether employee ID is available
		 */
		if (transactionID != null && !transactionID.isEmpty()) {
			/*
			 * Update employee query will be retrieved from EmployeeQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_TRANSFER));
				if(!(transaction.getAccountNo1().equals(transaction.getAccountNo2()))) {
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, transaction.getAccountNo1());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, transaction.getAccountNo2());
				preparedStatement.setFloat(CommonConstants.COLUMN_INDEX_THREE, transaction.getAmount());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, transaction.getTransactionId());
				preparedStatement.executeUpdate();
				}
				else {
					throw new AccountException();
				}
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		// Get the updated employee
		return getTransactionByID(transactionID);
	}
	
	/**
	 *
	 * @return ArrayList<String> Array of employee id list will be return
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	private ArrayList<String> getTransactionIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of employee IDs will be retrieved from EmployeeQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_TRANSFER_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return arrayList;
	}
	
	public void requestDelete(String transactionID) {

		if (transactionID != null && !transactionID.isEmpty()) {
			try {

				connection = DBConnectionUtil.getDBConnection();
				/*
				 * Query is available in EmployeeQuery.xml file and use
				 * insert_employee key to extract value of it
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_REQUEST_TRANSFER));
				connection.setAutoCommit(false);

				//Generate employee IDs
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, transactionID);
				// Add employee
				preparedStatement.execute();
				connection.commit();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end of
				 * transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}

		}
	}

	public ArrayList<Transaction> listDeleteRequests() {
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_ALL_REQUESTS_TRANSFER));
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				transaction.setAccountNo1(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				transaction.setAccountNo2(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				transaction.setAmount(resultSet.getFloat(CommonConstants.COLUMN_INDEX_FOUR));
				transactionList.add(transaction);
			}

			
		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return transactionList;
	}
	
	
public boolean getUserAuthenticate(String username, String password) {
		
		try {
			
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_AUTHENTICATE_LOGIN));
			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
				return false;
			}
			
			else {
				return true;
			}
			
		}catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			
			log.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			return false;
			
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
			 */
			try {
				
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (connection != null) {
					connection.close();
				}
				
			} catch (SQLException e) {
				
				log.log(Level.SEVERE, e.getMessage());
				e.printStackTrace();
			
			}
		}
		
		}
	
	/**
	 * @param username
	 * @return A boolean value depending on eligibility of username
	 */
	
	
	

}
