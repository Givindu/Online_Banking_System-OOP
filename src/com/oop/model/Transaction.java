package com.oop.model;

public class Transaction {
	
	private String TransactionId;
	
	private String AccountNo1;
	
	private String AccountNo2;
	
	private float Amount;
		
	
	public String getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	public String getAccountNo1() {
		return AccountNo1;
	}

	public void setAccountNo1(String accountNo1) {
		AccountNo1 = accountNo1;
	}

	public String getAccountNo2() {
		return AccountNo2;
	}

	public void setAccountNo2(String accountNo2) {
		AccountNo2 = accountNo2;
	}

	public float getAmount() {
		return Amount;
	}

	public void setAmount(float amount) {
		Amount = amount;
	}

}
