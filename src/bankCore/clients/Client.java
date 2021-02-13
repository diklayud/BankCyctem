package bankCore.clients;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import bankCore.Account;
import bankCore.Bank;
import bankCore.Log;
import bankCore.Logger;
import bankCore.exceptions.BankSystemException;
import bankCore.exceptions.WithdrawException;

/*an abstract class cannot be instantiated*/
public abstract class Client {

	private int id;
	private String name;
	private float balance;
	private Set<Account> accounts;
	// protected fields are accessible to sub classes
	protected float commissionRate;
	protected float interestRate;
	// LOGGER FIELD REMOVED ON PHASE 3

	// CTOR
	public Client(int id, String name, float balance) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.accounts = new HashSet<Account>();
	}

	// Methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public Collection<Account> getAccountsAsCollection() {
		return accounts;
	}
	
	public Account[] getAccountsAsArray() {
		return accounts.toArray(new Account[accounts.size()]);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[id=" + id + ", name=" + name + ", balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Client)) {
			return false;
		}
		Client other = (Client) obj;
		return id == other.id;
	}

	public void addAccount(Account account) throws BankSystemException {
		if (this.accounts.add(account)) {
			// log the operation
			Log log = new Log(this.id, "addAccount", account.getBalance());
			Logger.log(log);
			//
		} else {
			throw new BankSystemException("addAccount faild - already exists");
		}
	}

	/**
	 * returns the account of the specified id or null if not found
	 * 
	 * @param id
	 * @return
	 */
	public Account getAccount(int accountId) {
		for (Account currAccount : accounts) {
			if (currAccount.getId() == accountId) {
				return currAccount;
			}
		}
		return null; // you can decide to throw an exception instead
	}

	/**
	 * remove the account with the same id from this client & transfer the money to
	 * the clients balance. Log the operation via creating Log object with
	 * appropriate data and sending it to the Logger.log(..)method.
	 * 
	 * @throws BankSystemException
	 * 
	 */
	public void removeAccount(Account accountParameter) throws BankSystemException {
		for (Account currAccount : accounts) {
			if (accountParameter.equals(currAccount)) {
				this.balance += currAccount.getBalance(); // transfer the money to the client balance
				accounts.remove(currAccount); // remove the account
				// log the operation
				Log log = new Log(System.currentTimeMillis(), this.id, "removeAccount", currAccount.getBalance());
				Logger.log(log);
				return; // end the method here
			}
		}
		throw new BankSystemException("removeAccount failed - not found");
	}

	/**
	 * implement to add or remove the amount from clients balance according to the
	 * commission (which is now zero). Use the commission data member in your
	 * calculation). log the operation
	 */
	public void deposit(float amount) {
		makeDepositWithdraw(amount, true);
	}

	public void withdraw(float amount) throws WithdrawException {
		if (amount > this.balance) {
			throw new WithdrawException("withdraw faild - over draft", this.id, this.balance, amount);
		}
		makeDepositWithdraw(amount, false);
	}

	private void makeDepositWithdraw(float amount, boolean deposit) {
		String description = "deposit";
		if (deposit) {
			this.balance += amount;
		} else {
			this.balance -= amount;
			description = "withdraw";
		}
		// transfer commission from the client to the bank
		float commission = amount * this.commissionRate; // calculate the commission
		this.balance -= commission;
		Bank.getInstance().addCommission(commission);
		// log the operation
		Log log = new Log(System.currentTimeMillis(), this.id, description, amount);
		Logger.log(log);
		//
		// log the operation
		Log logCommission = new Log(System.currentTimeMillis(), this.id, "commission", commission);
		Logger.log(logCommission);
	}

	/**
	 * run over the accounts, calculate the amount to add according to the client
	 * interest (meanwhile it is zero) and add it to each account balance. Use the
	 * interest data member in your calculation. Log this operation.
	 */
	public void autoUpdateAccounts() {
		for (Account account : accounts) {
			if (account != null) {
				float interest = account.getBalance() * this.interestRate;
				account.setBalance(account.getBalance() + interest);
				// log the operation
				Log log = new Log(System.currentTimeMillis(), this.id, "autoUpdateAccounts", interest);
				Logger.log(log);
			}
		}
	}

	/**
	 * returns the sum of client balance + total account balance.
	 */
	public float getFortune() {
		float sum = this.balance;
		for (Account account : accounts) {
			if (account != null) {
				sum += account.getBalance();
			}
		}
		return sum;
	}

}
