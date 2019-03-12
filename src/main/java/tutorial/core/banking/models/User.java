package tutorial.core.banking.models;

import tutorial.core.banking.consistency.IUnit;

public class User implements IUnit {

	private String name;
	private long id;
	private long balance;
	private Account[] accounts;
	
	public User(long id,String name,Account[] accounts, long balance) {
		this.name=name;
		this.id=id;
		this.accounts = accounts;
		this.balance = balance;
	}

	public long getBalance() {
		return this.balance;
	}

	public void setBalance(long balance) {
		this.balance=balance;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name=name;
	}

	public Account[] getAccounts() {
		return this.accounts;
	}

	public void setAccount(Account[] accounts) {
		this.accounts=accounts;
	}
	
	public long getId() {
		return this.id;
	}

}
