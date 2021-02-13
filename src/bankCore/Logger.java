package bankCore;

import java.util.ArrayList;
import java.util.List;

public class Logger {
	
	// we can save the log instances in a collection
	private static List<Log> logs = new ArrayList<>();
	
	// Attribute
	private String driverName; // will be in use when working with database

	// CTOR
	public Logger(String driverName) {
		super();
		this.driverName = driverName;
	}
	
	// Method
	public static void log(Log log) {
		System.out.println(log);
		logs.add(log);
	}
	
	public static List<Log> getLogs() {
		return logs;
	}
	
	public String getDriverName() {
		return driverName;
	}
	
	

}
