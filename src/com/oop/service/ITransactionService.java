package com.oop.service;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.oop.exception.AccountException;
import com.oop.model.Transaction;

public interface ITransactionService {
	
	/** Initialise logger */
	public static final Logger log = Logger.getLogger(ITransactionService.class.getName());


	/**
	 * Add employees for employee table
	 * @param employee
	 */
	public void addTransaction(Transaction transaction)throws AccountException;

	/**
	 * Get a particular Employee
	 * 
	 * @param empoyeeID
	 * @return Employee
	 */
	public Transaction getTransactionByID(String transactionID);
	
	/**
	 * Get all list of employees
	 * 
	 * @return ArrayList<Employee>
	 */
	public ArrayList<Transaction> getTransactions(String accountNo);
	
	/**
	 * Update existing employee
	 * @param employeeID
	 * @param employee
	 * 
	 * @return
	 */
	public Transaction updateTransaction(String transactionID, Transaction transaction) throws AccountException;

	/**
	 * Remove existing employee
	 * 
	 * @param employeeID
	 */
	public void deleteTransaction(String transactionID);
	
	public void requestDelete(String transactionID);
	
	public ArrayList<Transaction> listDeleteRequests();

}
