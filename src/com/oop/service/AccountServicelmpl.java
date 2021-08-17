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

import com.oop.model.Account;
import com.oop.exception.AgeException;
import com.oop.util.CommonConstants;
import com.oop.util.CommonUtil;
import com.oop.util.DBConnectionUtil;
import com.oop.util.QueryUtil;


public class AccountServicelmpl implements NAccountService {
	

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(AccountServicelmpl.class.getName());

	private static Connection connection;

	private static Statement statement;
	private PreparedStatement preparedStatement;
	static{
		//create table or drop if exist
		createAccountTable();
		createRequestTable();
	}

	

	/**
	 * This method initially drop existing Account table in the database and
	 * recreate table structure to insert account entries
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
	public static void createAccountTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_TABLE_ACCOUNT));
			// Create new accounts table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_TABLE_ACCOUNT));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
	
	/**
	 * This method initially drop existing Account table in the database and
	 * recreate table structure to insert account entries
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
	public static void createRequestTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_Drop_REQUEST_TABLE_ACCOUNT));
			// Create new accounts table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_REQUEST_TABLE_ACCOUNT));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add set of accounts for as a batch from the selected account List
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
	public void createAccount(Account account) throws AgeException {
		
		String Account_number = CommonUtil.generateAccountIDs(getAccountNumbers());
	
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in AccQuery.xml file and use
			 * insert_account key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_Accounts));
			
			connection.setAutoCommit(false);
			
			//Generate account numbers
			account.setAccount_number(Account_number);
			if(Integer.parseInt(account.getAge())>18){
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, account.getAccount_number());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, account.getOwner_name());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, account.getNic());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, account.getAge());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, account.getGender());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, account.getAcc_type());
			preparedStatement.setInt(CommonConstants.COLUMN_INDEX_SEVEN, account.getAcc_balance());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, account.getPassword());
			
			// create account
			preparedStatement.execute();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_USER));
			
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, account.getAccount_number());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, account.getPassword());
			
			preparedStatement.execute();
			connection.commit();
			}
			else{
				throw new AgeException();
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
	 * Account details can be retrieved based on the provided Account_number
	 * 
	 * @param account_number
	 *            -Account details are filtered based on the ID
	 * 
	 * @see #actionOnAccount()
	 */
	//@Override
	public Account getAccountbyId(String Account_number) {

		return actionOnAccount(Account_number).get(0);
	}
	
	/**
	 * Get all list of accounts
	 * 
	 * @return ArrayList<Account> 
	 * 						- Array of account list will be return
	 * 
	 * @see #actionOnAccount()
	 */
	@Override
	public ArrayList<Account> getAccounts() {
		
		return actionOnAccount(null);
	}

	/**
	 * This method delete an account based on the provided account number
	 * 
	 * @param account number
	 *            - Delete account according to the filtered account details
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
	public void requestToDelete(String Account_number) {

		// Before deleting check whether Account number is available
		
		if (Account_number != null && !Account_number.isEmpty()) {
			/*
			 * Remove Account query will be retrieved from AccQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
			.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_Accounts));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, Account_number);
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
	 * This performs GET account by ID and Display all employees
	 * 
	 * @param acccount_number
	 *            number of the account to remove or select from the list

	 * @return ArrayList<Account> Array of account list will be return
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
	 * @see #getAccounts()
	 * @see #getAccountNumber(String)
	 */
	private ArrayList<Account> actionOnAccount(String Account_number) {

		
		ArrayList<Account> accountList = new ArrayList<Account>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching account it checks whether account number is
			 * available
			 */
			if (Account_number != null && !Account_number.isEmpty()) {
				/*
				 * Get account by account number query will be retrieved from
				 * AccQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_Account_BY_ID));
				
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, Account_number);
				
			}
			/*
			 * If account number is not provided for get account option it display
			 * all accounts
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_Accounts));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Account account = new Account();
				account.setAccount_number(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				account.setOwner_name(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				account.setNic(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				account.setAge(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				account.setGender(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				account.setAcc_type(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				account.setAcc_balance(resultSet.getInt(CommonConstants.COLUMN_INDEX_SEVEN));
				
				accountList.add(account);
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
		return accountList;
	}

	/**
	 * Get the updated account
	 * 
	 * @param account number
	 *            ID of the account to remove or select from the list
	 * 
	 * @return return the Account object
	 * 
	 */
	@Override
	public Account updateAccount(String Account_number, Account account) throws AgeException {

		/*
		 * Before fetching employee it checks whether Account number is available
		 */
		if (Account_number != null && !Account_number.isEmpty()) {
			/*
			 * Update account query will be retrieved from AccountQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
				.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_Accounts));
				if(Integer.parseInt(account.getAge())>18){
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, account.getAccount_number());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, account.getOwner_name());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, account.getNic());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, account.getAge());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, account.getGender());
				System.out.println("met"+account.getGender());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, account.getAcc_type());
				preparedStatement.setInt(CommonConstants.COLUMN_INDEX_SIX, account.getAcc_balance());
				
				preparedStatement.executeUpdate();
				}
				else{
					throw new AgeException();
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
		// Get the updated accounts
		return getAccountbyId(Account_number);
	}
	
	/**
	 *
	 * @return ArrayList<String> Array of account number list will be return
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
	private ArrayList<String> getAccountNumbers(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of account numbers will be retrieved from AccQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_Account_numbers));
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

	@Override
	public void Balance(String Account_number) {
		// TODO Auto-generated method stub
		
	}
	
public void requestDelete(String accountNumber) {
		
		if (accountNumber != null && !accountNumber.isEmpty()) {
			try {
		
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in EmployeeQuery.xml file and use
			 * insert_employee key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_REQUEST_ACCOUNT));
			connection.setAutoCommit(false);
			
			//Generate employee IDs
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, accountNumber);
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
	
	public ArrayList<Account> listDeleteRequests() {
		ArrayList<Account> accountList = new ArrayList<Account>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_ALL_REQUESTS_ACCOUNT));
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Account account = new Account();
				account.setAccount_number(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				account.setOwner_name(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				account.setNic(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				account.setAge(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				account.setGender(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				account.setAcc_type(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				account.setAcc_balance(resultSet.getInt(CommonConstants.COLUMN_INDEX_SEVEN));
				
				accountList.add(account);
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
		return accountList;
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
}

