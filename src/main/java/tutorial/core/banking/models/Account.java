package tutorial.core.banking.models;

public class Account {

	private String accountNumber;
	private double balance;
	private boolean isBlocked=false;
	private double maxAllowedBalance;
	
	public Account(String accountNumber,double balance,boolean isBlocked) {
		this.accountNumber=accountNumber;
		this.balance=balance;
		this.isBlocked=isBlocked;
		this.maxAllowedBalance=Double.MAX_VALUE;
	}
	
	public Account(String accountNumber,double balance,boolean isBlocked,double maxAllowedBalance){
		
		this(accountNumber,balance,isBlocked);
		this.maxAllowedBalance=maxAllowedBalance;
	}
	
	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance=balance;
	}

	public void setIsBlocked(boolean isBlocked) {
		this.isBlocked=isBlocked;
	}
	
	public boolean getIsBlocked() {
		return this.isBlocked;
	}
	 
	public double getMaxAllowedBalance() {
		return this.maxAllowedBalance;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

}
