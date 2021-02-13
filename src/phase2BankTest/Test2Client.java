package phase2BankTest;

import bankCore.Account;
import bankCore.clients.Client;

public class Test2Client {

	public static void main(String[] args) {
	
//		Client cl = new Client(111, "aaa", 25_000); // Client is abstract class, cannot create an instance of it
				
		Account account = new Account(6589, 50_000);
//		cl.addAccount(account);

	}

}
