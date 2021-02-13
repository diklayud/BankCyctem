package bankCore.exceptions;

public class WithdrawException extends BankSystemException {

	private int clientId;
	private float currentClientBalance;
	private float withdrawAmount;
		
	private static final long serialVersionUID = 1L;	
	
	// field get methods
	public int getClientId() {
		return clientId;
	}

	public float getCurrentClientBalance() {
		return currentClientBalance;
	}

	public float getWithdrawAmount() {
		return withdrawAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// CTORs
	public WithdrawException(String message, int clientId, float currentClientBalance, float withdrawAmount) {
		super(message);
		this.clientId = clientId;
		this.currentClientBalance = currentClientBalance;
		this.withdrawAmount = withdrawAmount;
	}

	public WithdrawException() {
	}

	public WithdrawException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WithdrawException(String message, Throwable cause) {
		super(message, cause);
	}

	public WithdrawException(String message) {
		super(message);
	}

	public WithdrawException(Throwable cause) {
		super(cause);
	}

}
