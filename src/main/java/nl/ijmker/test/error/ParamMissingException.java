package nl.ijmker.test.error;

public class ParamMissingException extends RuntimeException {

	private static final long serialVersionUID = -481238145145778352L;

	/**
	 * @param paramName
	 */
	public ParamMissingException(String paramName) {
		super("Parameter '" + paramName + "' cannot be empty");
	}
}
