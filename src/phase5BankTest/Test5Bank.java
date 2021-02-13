package phase5BankTest;

import bankCore.Logger;

import bankCore.Bank;
import bankCore.clients.GoldClient;
import bankCore.clients.PlatinumClient;
import bankCore.clients.RegularClient;
import bankCore.exceptions.BankSystemException;

public class Test5Bank {

	public static void main(String[] args) {

		try {
			Bank bank = Bank.getInstance();

			bank.addClient(new RegularClient(101, "aaa", 0));
			bank.addClient(new GoldClient(102, "bbb", 0));
			bank.addClient(new PlatinumClient(103, "ccc", 0));

			bank.getClient(101).deposit(100);
			bank.getClient(102).deposit(1000);
			bank.getClient(103).deposit(500);

			bank.printClientList();
			
			System.out.println(Logger.getLogs());

		} catch (BankSystemException e) {
			e.printStackTrace();
		}
	}

}
