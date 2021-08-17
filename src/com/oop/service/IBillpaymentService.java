package com.oop.service;

import com.oop.exception.BillNumberException;
import com.oop.model.Billpayment;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

public interface IBillpaymentService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IBillpaymentService.class.getName());


	/**
	 * Add employees for bill payment table
	 * @param employee
	 */
	public void addBillpayment(Billpayment billpayment) throws BillNumberException;

	/**
	 * Get a particular bill payment
	 * 
	 * @param billID
	 * @return billpayment
	 */
	public Billpayment getBillpaymentByID(String billID);
	
	/**
	 * Get all list of bill payments
	 * 
	 * @return ArrayList<billpayment>
	 */
	public ArrayList<Billpayment> getBillpayment(String billID);
	
	/**
	 * Update existing bill payment
	 * @param billID
	 * @param billpayment
	 * 
	 * @return
	 */
	public Billpayment updateBillpayment(String billID, Billpayment billpayment);

	/**
	 * Remove existing billpayment
	 * 
	 * @param billID
	 */
	public void removeBillpayment(String billID);

}
