package nl.ijmker.test.error;

public class InvalidActionException extends RuntimeException {

	private static final long serialVersionUID = -481238145145778352L;

	/**
	 * @param paramName
	 */
	public InvalidActionException(String message) {
		super(message);
	}
}
