package com.oop.model;

public class FixedDeposit {
	
	private String DepositID;
	
	private String Interest;
	
	private String Duration;
	
	private String AccountNum;
	
	private String Amount;

	public String getDepositID() {
		return DepositID;
	}

	public void setDepositID(String depositID) {
		this.DepositID = depositID;
	}
	
	public String getInterest() {
		return Interest;
	}

	public void setInterest(String interest) {
		this.Interest = interest;
	}
	
	public String getDuration() {
		return Duration;
	}

	public void setDuration(String duration) {
		this.Duration = duration;
	}

	public String getAccountNum() {
		return AccountNum;
	}

	public void setAccountNum(String accountNum) {
		this.AccountNum = accountNum;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		this.Amount = amount;
	}

	public String toString() {
		
		return "Deposit ID = " + DepositID + "\n" + "Interest = " + Interest + "\n" + "Duration = " + Duration + "\n"
				+ "Account Number = " + AccountNum + "\n" + "Amount = " + Amount;
	}
	
}

