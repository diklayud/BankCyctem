package phase2BankTest;

import bankCore.Bank;
import bankCore.clients.PlatinumClient;
import bankCore.clients.RegularClient;
import bankCore.exceptions.BankSystemException;

public class Test4Bank {

	public static void main(String[] args) {

		try {
			Bank bank = Bank.getInstance();

			bank.addClient(new RegularClient(101, "aaa", 0));
			bank.addClient(new PlatinumClient(102, "bbb", 0));

			bank.getClient(101).deposit(100);
			bank.getClient(102).deposit(100);

			bank.printClientList();

		} catch (BankSystemException e) {
			e.printStackTrace();
		}
	}

}
