package com.oop.model;

public class Account {
	private String Account_number;
	private String Owner_name;
	private String Nic;
	private String Age;
	private String Gender;
	private String Acc_type;
	private int Acc_balance;
	private String Password;
	
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Account(){}
	
	public Account(String account_number,String owner_name,String nic,String age,String gender,String acc_type,int  acc_balance){
		Account_number=account_number;
		Owner_name=owner_name;
		Nic=nic;
		Age=age;
		Gender=gender;
		Acc_type=acc_type;
		Acc_balance=acc_balance;
	}

	public String getAccount_number() {
		return Account_number;
	}

	public void setAccount_number(String account_number) {
		Account_number = account_number;
	}

	public String getOwner_name() {
		return Owner_name;
	}

	public void setOwner_name(String owner_name) {
		Owner_name = owner_name;
	}

	public String getNic() {
		return Nic;
	}

	public void setNic(String nic) {
		Nic = nic;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getAcc_type() {
		return Acc_type;
	}

	public void setAcc_type(String acc_type) {
		Acc_type = acc_type;
	}
	public int getAcc_balance(){
		return Acc_balance;
	}
	public void setAcc_balance(int acc_balance)
	{
	     Acc_balance=acc_balance;	
	}
     public String Details() {
		
		return "Account Number = " +Account_number + "\n" + "Customer Name = " + Owner_name + "\n" + "Nic = " + Nic + "\n"
				+ "Age = " + Age + "\n" + "Gender = " + Gender + "\n" + "Account type = "
				+ Acc_type; 
	}
	
	

}
