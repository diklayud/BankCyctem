package bankCore;

import java.util.Date;

public class Log {

	// Attributes
	private long timeStamp; // ts=timeStamp: date and hour/time
	private int clientId;
	private String description;
	private float amount;

	// CTOR 1 - user specify the time
	public Log(long timeStamp, int clientId, String description, float amount) {
		super();
		this.timeStamp = timeStamp;
		this.clientId = clientId;
		this.description = description;
		this.amount = amount;
	}

	// CTOR 2 - no need to specify time
	public Log(int clientId, String description, float amount) {
		this(System.currentTimeMillis(), clientId, description, amount); // this()= call CTOR1
	}

	// Method
//	public String getData() {
//		return "Log [timeStamp=" + timeStamp + ", clientId=" + clientId + ", description="
//				+ description + ", amount=" + amount + "]";
//	}

	@Override
	public String toString() {
		Date date = new Date(timeStamp);
		return "Log [date=" + date + ", clientId=" + clientId + ", description=" + description + ", amount="
				+ amount + "]";
	}
	
	
	
}
