package nl.ijmker.test.error;

public class CSRFException extends RuntimeException {

	private static final long serialVersionUID = -481238145145778352L;

	/**
	 * @param varName
	 */
	public CSRFException(String csrfToken, String storedToken) {
		super("Token " + csrfToken + " does not match " + storedToken);
	}
}
