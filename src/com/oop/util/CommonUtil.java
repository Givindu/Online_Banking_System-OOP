/**
 * 2018 OOP
 * 
 * @author Udara Samaratunge  Department of Software Engineering, SLIIT 
 * 
 * @version 1.0
 * Copyright: SLIIT, All rights reserved
 * 
 */
package com.oop.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.oop.service.NAccountService;

import com.oop.util.CommonConstants;


public class CommonUtil {

	/** Initialise logger */
	public static final Logger log = Logger.getLogger(NAccountService.class.getName());

	public static final Properties properties = new Properties();

	static {
		try {
			
			// Read the property only once when load the class
			properties.load(QueryUtil.class.getResourceAsStream(CommonConstants.PROPERTY_FILE));
			
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add new Account number
	 * 
	 * @param arrayList
	 * @return
	 */
	public static String generateAccountIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants. ACCOUNT_NUMBER_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants. ACCOUNT_NUMBER_PREFIX + next;
		}
		return id;
	}
	
	public static String generateTransferIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants.TRANSFER_ID_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants.TRANSFER_ID_PREFIX + next;
		}
		return id;
	}
	
	public static String generateFdIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants.FIXEDDEPOSIT_ID_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants.FIXEDDEPOSIT_ID_PREFIX + next;
		}
		return id;
	}

	/*public static String generateAccountNumber(ArrayList<String> accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}*/
}

