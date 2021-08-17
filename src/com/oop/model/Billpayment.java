package com.oop.model;

/**
 * This is the Billpayment model class
 */
public class Billpayment {

	private String AccNum;
	
	private String BillID;
	
	private String Name;

	private float Amount;

	private String Bank;

	


	/**
	 * @return the Account Number
	 */
	public String getAccNum() {
		return AccNum;
	}

	/**
	 * @param AccNum the AccNum to set
	 */
	public void setAccNum(String accNum) {
		this.AccNum = accNum;
	}
	
	
	public String getBillID() {
		return BillID;
	}

	public void setBillID(String billID) {
		this.BillID = billID;
	}
	
	

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.Name = name;
	}

	/**
	 * @return the Amount
	 */
	public float getAmount() {
		return Amount;
	}

	/**
	 * @param Amount the Amount to set
	 */
	public void setAmount(float amount) {
		this.Amount = amount;
	}

	/**
	 * @return the Bank
	 */
	public String getBank() {
		return Bank;
	}

	/**
	 * @param Bank the Bank to set
	 */
	public void setBank(String bank) {
		this.Bank = bank;
	}


	//@Override
	/*public String toString() {
		
		return "Account number = " + AccNum + "\n" + "Name = " + Name + "\n" + "Amount = " + Amount + "\n"
				+ "Bank = " + Bank;
		}*/
}
