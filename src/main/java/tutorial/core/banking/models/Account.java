package tutorial.core.banking.models;

public class Account {

	private String email;
	private String phoneNumber;

	private String accountNumber;
	private double balance;
	private boolean isBlocked=false;
	private double maxAllowedBalance;
	private AccountType accountType;
	
	public Account(String accountNumber,double balance,String email,AccountType accountType) {
		this.accountNumber=accountNumber;
		this.balance=balance;
		this.email=email;
		this.accountType=accountType;
		this.maxAllowedBalance=Double.MAX_VALUE;
	}
	
	public Account(String accountNumber,double balance,String email,AccountType accountType,double maxAllowedBalance){
		
		this(accountNumber,balance,email,accountType);
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
	
	public void setType(AccountType accountType) {
		this.accountType=accountType;
	}
	
	public AccountType getType() {
		return this.accountType;
	}
	 
	public double getMaxAllowedBalance() {
		return this.maxAllowedBalance;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


}
