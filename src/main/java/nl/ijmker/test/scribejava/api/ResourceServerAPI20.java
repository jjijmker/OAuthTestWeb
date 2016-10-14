package nl.ijmker.test.scribejava.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.scribejava.apis.google.GoogleJsonTokenExtractor;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;

import nl.ijmker.test.util.ConfigUtil;

public class ResourceServerAPI20 extends DefaultApi20 {
	
	private static final Logger LOG = LoggerFactory.getLogger(ResourceServerAPI20.class);

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

    /* (non-Javadoc)
     * @see com.github.scribejava.core.builder.api.DefaultApi20#getAccessTokenExtractor()
     */
    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
    	
    	LOG.info("JANNEMAN2");
    	
        return GoogleJsonTokenExtractor.instance();
    }
}
