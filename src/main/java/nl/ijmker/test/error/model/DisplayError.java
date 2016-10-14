package nl.ijmker.test.error.model;

public class DisplayError {

	private DisplayErrorOrigin origin;

	private int statusCode;

	private String message;

	private String stackTrace;

	private String extraInfo;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public DisplayErrorOrigin getOrigin() {
		return origin;
	}

	public void setOrigin(DisplayErrorOrigin origin) {
		this.origin = origin;
	}
}
