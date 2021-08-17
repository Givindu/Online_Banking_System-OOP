package com.oop.service;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;


import com.oop.exception.AmountException;
import com.oop.model.FixedDeposit;

public interface IFixedDeposit {

	public static final Logger log = Logger.getLogger(IFixedDeposit.class.getName());

	public void addFixedDeposit(FixedDeposit fixeddeposit) throws AmountException;

	public FixedDeposit getFixedDepositID(String depositID);

	public ArrayList<FixedDeposit> getFixedDeposit(String AccNo);

	public FixedDeposit updateFixedDeposit(String depositID, FixedDeposit fixeddeposit);

	public void removeFixedDeposit(String depositID);
	
}
