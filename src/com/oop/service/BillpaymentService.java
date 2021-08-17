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

import com.oop.exception.BillNumberException;
import com.oop.model.Billpayment;
import com.oop.util.CommonConstants;
import com.oop.util.CommonUtil;
import com.oop.util.DBConnectionUtil;
import com.oop.util.QueryUtil;

/**
 * Contract for the implementation of Bill payment Service .
 */
public class BillpaymentService implements IBillpaymentService {
	

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(BillpaymentService.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		
		
		
		createBillpaymentTable();
		createRequestTable();
	}

	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing bill payment table in the database and
	 * recreate table structure to insert bill payment entries
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
	public static void createBillpaymentTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			//statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_TABLE_BillPayment));
			// Create new employees table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_TABLE_BillPayment));
			

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
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_Drop_REQUEST_TABLE_BillPayment));
			// Create new employees table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_REQUEST_TABLE_BillPayment));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add set of bill payments for as a batch from the selected bill payment List
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
	public void addBillpayment(Billpayment billpayment) throws BillNumberException {

		//String BillID = CommonUtil.generateIDs(getBillIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in BillpaymentQuery.xml file and use
			 * insert_bill payment key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_billpayment));
			connection.setAutoCommit(false);
			
			//Generate bill payment IDs
			//billpayment.setBillID((BillID));
			if(billpayment.getBillID().length()==4) {
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, billpayment.getAccNum());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, billpayment.getBillID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, billpayment.getName());
			preparedStatement.setFloat(CommonConstants.COLUMN_INDEX_FOUR, billpayment.getAmount());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, billpayment.getBank());

			// Add bill payment
			preparedStatement.execute();
			connection.commit();
			}
			else {
				throw new BillNumberException();
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
	 * Employee details can be retrieved based on the provided bill ID
	 * 
	 * @param billID
	 *            - bill payment details are filtered based on the ID
	 * 
	 * @see #actionOnBillpayment()
	 */
	@Override
	public Billpayment getBillpaymentByID(String BillID) {

		ArrayList<Billpayment>billpaymentList = new ArrayList<Billpayment>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_billpayment));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, BillID);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Billpayment billpayment = new Billpayment();
				billpayment.setAccNum(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				billpayment.setBillID(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				billpayment.setName(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				billpayment.setAmount(resultSet.getFloat(CommonConstants.COLUMN_INDEX_FOUR));
				billpayment.setBank(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				billpaymentList.add(billpayment);
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
		return billpaymentList.get(0);
	}
	
	/**
	 * Get all list of bill payments
	 * 
	 * @return ArrayList<Billpayment> 
	 * 						- Array of bill payment list will be return
	 * 
	 * @see #actionOnBillpayment()
	 */
	@Override
	public ArrayList<Billpayment> getBillpayment(String AccNo) {
		
		ArrayList<Billpayment>billpaymentList = new ArrayList<Billpayment>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_billpayment));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, AccNo);
	
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Billpayment billpayment = new Billpayment();
				billpayment.setAccNum(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				billpayment.setBillID(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				billpayment.setName(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				billpayment.setAmount(resultSet.getFloat(CommonConstants.COLUMN_INDEX_FOUR));
				billpayment.setBank(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				billpaymentList.add(billpayment);
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
		return billpaymentList;
	}

	/**
	 * This method delete an bill payment based on the provided ID
	 * 
	 * @param billID
	 *            - Delete bill payment according to the filtered bill payment details
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
	public void removeBillpayment(String billID) {

		// Before deleting check whether bill ID is available
		if (billID != null && !billID.isEmpty()) {
			/*
			 * Remove bill payment query will be retrieved from BillpaymentQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_billpayment));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, billID);
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
	 * This performs GET bill payment by ID and Display all bill payments
	 * 
	 * @param billID
	 *            ID of the bill payment to remove or select from the list

	 * @return ArrayList<billpayment> Array of bill payment list will be return
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
	 * @see #getBillpayments()
	 * @see #getBillpaymentByID(String)
	 */
	private ArrayList<Billpayment> actionOnBillpayment(String billID) {

		ArrayList<Billpayment>billpaymentList = new ArrayList<Billpayment>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching bill payment it checks whether bill ID is
			 * available
			 */
			if (billID != null && !billID.isEmpty()) {
				/*
				 * Get bill payment by ID query will be retrieved from
				 * BillpaymentQuery.xml
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_billpayment));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, billID);
			}
			/*
			 * If bill ID is not provided for get bill payment option it display
			 * all bill payments
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_billpayment));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Billpayment billpayment = new Billpayment();
				billpayment.setAccNum(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				billpayment.setBillID(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				billpayment.setName(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				billpayment.setAmount(resultSet.getFloat(CommonConstants.COLUMN_INDEX_FOUR));
				billpayment.setBank(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				billpaymentList.add(billpayment);
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
		return billpaymentList;
	}

	/**
	 * Get the updated bill payment
	 * 
	 * @param billID
	 *            ID of the bill payment to remove or select from the list
	 * 
	 * @return return the bill payment object
	 * 
	 */
	@Override
	public Billpayment updateBillpayment(String billID, Billpayment billpayment) {

		/*
		 * Before fetching bill payment it checks whether bill ID is available
		 */
		if (billID != null && !billID.isEmpty()) {
			/*
			 * Update bill payment query will be retrieved from BillpaymentQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
							.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_billpayment));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, billpayment.getAccNum());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, billpayment.getName());
				preparedStatement.setFloat(CommonConstants.COLUMN_INDEX_THREE, billpayment.getAmount());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, billpayment.getBank());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, billpayment.getBillID());
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
		// Get the updated bill payment
		return getBillpaymentByID(billID);
	}
	
	/**
	 *
	 * @return ArrayList<String> Array of bill id list will be return
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
	private ArrayList<String> getBillIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of bill IDs will be retrieved from BillpaymentQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_billpayment_IDS));
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







	public void requestDelete(String billID) {

		if (billID != null && !billID.isEmpty()) {
			try {

				connection = DBConnectionUtil.getDBConnection();
				/*
				 * Query is available in EmployeeQuery.xml file and use
				 * insert_employee key to extract value of it
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_REQUEST_BillPayment));
				connection.setAutoCommit(false);

				//Generate employee IDs
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, billID);
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

	public ArrayList<Billpayment> listDeleteRequests() {
		ArrayList<Billpayment> billpaymentList = new ArrayList<Billpayment>();
		try {
			connection = DBConnectionUtil.getDBConnection();

			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_ALL_REQUESTS_BillPayment));

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Billpayment billpayment = new Billpayment();
				billpayment.setAccNum(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				billpayment.setBillID(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				billpayment.setName(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				billpayment.setAmount(resultSet.getFloat(CommonConstants.COLUMN_INDEX_FOUR));
				billpayment.setBank(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				billpaymentList.add(billpayment);
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
		return billpaymentList;
	}
}
