package nl.ijmker.test.action;

/**
 * @author Jan IJmker
 * 
 *         This command can be applied to different servers, and needs to know
 *         what action that server is expected to execute with the results.
 *
 */
public abstract class OAuthCommand extends BaseCommand {

	private String server;
	private String resourceAction;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getResourceAction() {
		return resourceAction;
	}

	public void setResourceAction(String resourceAction) {
		this.resourceAction = resourceAction;
	}
}
