package com.oop.service;

import com.oop.exception.AgeException;
import com.oop.model.Account;

import java.util.ArrayList;
//import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactoryConfigurationError;

/**
 * Contract for the implementation of Account Service .
 * 
 */
public interface NAccountService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(NAccountService.class.getName());


	/**
	 * Add accounts for accounts table
	 * @param account
	 */
	public void createAccount(Account account)throws AgeException;

	/**
	 * Get a particular Account
	 * 
	 * @param account number
	 * @return Account
	 */
	public Account getAccountbyId(String Account_number);
	
	/**
	 * Get all list of accounts
	 * 
	 * @return ArrayList<Accounts>
	 */
	
	//public ArrayList<String> getAccountNumber();
	
	
	public ArrayList<Account> getAccounts();
	
	/**
	 * Update existing account
	 * @param account number
	 * @param account
	 * 
	 * @return
	 */
	public Account updateAccount(String Account_number, Account account) throws AgeException;

	/**
	 * Remove existing account
	 * 
	 * @param account number
	 */
	public void requestToDelete(String Account_number);
	
	
	public void Balance(String Account_number);

}



