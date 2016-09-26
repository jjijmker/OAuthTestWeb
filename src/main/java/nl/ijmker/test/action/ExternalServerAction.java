package nl.ijmker.test.action;

public abstract class ExternalServerAction extends BaseAction {

	private String serverType;

	/**
	 * @return
	 */
	public String getServerType() {
		return serverType;
	}

	/**
	 * @param serverType
	 */
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
}
