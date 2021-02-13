package phase2BankTest;

import bankCore.Log;
import bankCore.Logger;

public class Test1Logs {

	public static void main(String[] args) {
		
		Log log1 = new Log(101, "test log 1", 350);
		Log log2 = new Log(102, "test log 2", 400);

		Logger logger = new Logger(null);
		logger.log(log1);
		logger.log(log2);
	}

}
