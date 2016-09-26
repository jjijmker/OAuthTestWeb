package nl.ijmker.test.scribejava.api;

import com.github.scribejava.core.builder.api.DefaultApi20;

import nl.ijmker.test.util.ConfigUtil;

public class ResourceServerAPI20 extends DefaultApi20 {

	private String resourceServer;

	/**
	 * @param resourceServer
	 */
	public ResourceServerAPI20(String resourceServer) {
		setResourceServer(resourceServer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.scribejava.core.builder.api.DefaultApi20#
	 * getAccessTokenEndpoint()
	 */
	@Override
	public String getAccessTokenEndpoint() {
		return ConfigUtil.getOAuth2AccessTokenURL(getResourceServer());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.scribejava.core.builder.api.DefaultApi20#
	 * getAuthorizationBaseUrl()
	 */
	@Override
	protected String getAuthorizationBaseUrl() {
		return ConfigUtil.getOAuth2AuthorizationURL(resourceServer);
	}

	/**
	 * @return
	 */
	public String getResourceServer() {
		return resourceServer;
	}

	/**
	 * @param resourceServer
	 */
	public void setResourceServer(String resourceServer) {
		this.resourceServer = resourceServer;
	}

}
