package nl.ijmker.test.action;

public abstract class LinkedInAction extends BaseAction {

	private static final String SERVER_LINKEDIN = "linkedin";

	/**
	 * @return
	 */
	public String getServer() {
		return SERVER_LINKEDIN;
	}
}
