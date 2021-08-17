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

import com.oop.exception.AmountException;
import com.oop.model.FixedDeposit;
import com.oop.model.Transaction;
import com.oop.util.CommonConstants;
import com.oop.util.CommonUtil;
import com.oop.util.DBConnectionUtil;
import com.oop.util.QueryUtil;

public class FixedDepositImpl implements IFixedDeposit {

	public static final Logger log = Logger.getLogger(FixedDepositImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{

		createFixedDepositTable();
		createRequestTable();

	}
	private PreparedStatement preparedStatement;

	public static void createFixedDepositTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();

			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_TABLE_FD));

			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_TABLE_FD));

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
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_Drop_REQUEST_TABLE_fd));
			// Create new employees table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_REQUEST_TABLE_fd));

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
	

	public void addFixedDeposit(FixedDeposit fixeddeposit) throws AmountException{

		String depositID = CommonUtil.generateFdIDs(getDepositID());

		try {
			connection = DBConnectionUtil.getDBConnection();

			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_FIXEDDEPOSIT));
			connection.setAutoCommit(false);

			fixeddeposit.setDepositID(depositID);
			if(Float.parseFloat(fixeddeposit.getAmount())>5000) {
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, fixeddeposit.getDepositID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, fixeddeposit.getInterest());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, fixeddeposit.getDuration());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, fixeddeposit.getAccountNum());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, fixeddeposit.getAmount());

			preparedStatement.execute();
			connection.commit();
			}
			else {
				throw new AmountException();
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {

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


	public void requestDelete(String depositID) {

		if (depositID != null && !depositID.isEmpty()) {
			try {

				connection = DBConnectionUtil.getDBConnection();
				/*
				 * Query is available in EmployeeQuery.xml file and use
				 * insert_employee key to extract value of it
				 */
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_REQUEST_fd));
				connection.setAutoCommit(false);

				//Generate employee IDs
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, depositID);
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
	
	
	public ArrayList<FixedDeposit> listDeleteRequests() {
		ArrayList<FixedDeposit> fixeddepositList = new ArrayList<FixedDeposit>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_ALL_REQUESTS_fd));
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				FixedDeposit fixeddeposit = new FixedDeposit();
				fixeddeposit.setDepositID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				fixeddeposit.setInterest(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				fixeddeposit.setDuration(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				fixeddeposit.setAccountNum(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				fixeddeposit.setAmount(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				fixeddepositList.add(fixeddeposit);
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
		return fixeddepositList;
	}
	


	public FixedDeposit getFixedDepositID(String depositID) {
		return actionOnFixedDeposit(depositID).get(0);
	}

	public ArrayList<FixedDeposit> getFixedDeposit(String AccNo) {

		ArrayList<FixedDeposit> fixeddepositList = new ArrayList<FixedDeposit>();
		try {
			connection = DBConnectionUtil.getDBConnection();

			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_FIXEDDEPOSIT));
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, AccNo);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				FixedDeposit fixeddeposit = new FixedDeposit();
				fixeddeposit.setDepositID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				fixeddeposit.setInterest(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				fixeddeposit.setDuration(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				fixeddeposit.setAccountNum(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				fixeddeposit.setAmount(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));

				fixeddepositList.add(fixeddeposit);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {

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
		return fixeddepositList;
	}


	public void removeFixedDeposit(String depositID) {

		if (depositID != null && !depositID.isEmpty()) {

			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_FIXEDDEPOSIT));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, depositID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {

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
	

	private ArrayList<FixedDeposit> actionOnFixedDeposit(String depositID) {

		ArrayList<FixedDeposit> fixeddepositList = new ArrayList<FixedDeposit>();
		try {
			connection = DBConnectionUtil.getDBConnection();

			if (depositID != null && !depositID.isEmpty()) {

				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_FIXEDDEPOSIT));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, depositID);
			}

			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_FIXEDDEPOSIT));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				FixedDeposit fixeddeposit = new FixedDeposit();
				fixeddeposit.setDepositID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				fixeddeposit.setInterest(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				fixeddeposit.setDuration(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				fixeddeposit.setAccountNum(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				fixeddeposit.setAmount(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));

				fixeddepositList.add(fixeddeposit);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {

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
		return fixeddepositList;
	}

	public FixedDeposit updateFixedDeposit(String depositID, FixedDeposit fixeddeposit) {

		if (depositID != null && !depositID.isEmpty()) {

			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_FIXEDDEPOSIT));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, fixeddeposit.getDepositID());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, fixeddeposit.getInterest());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, fixeddeposit.getDuration());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, fixeddeposit.getAccountNum());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, fixeddeposit.getAmount());

				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {

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

		return getFixedDepositID(depositID);
	}

	private ArrayList<String> getDepositID(){

		ArrayList<String> arrayList = new ArrayList<String>();

		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_FIXEDDEPOSIT_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {

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

}
