package nl.ijmker.test.action;

/**
 * @author Jan IJmker
 * 
 *         This command can be applied to different servers, and needs to know
 *         what action that server is expected to execute with the results.
 *
 */
public abstract class GenericCommand extends SpecificCommand {

	private String server;
	private String resource;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
}
