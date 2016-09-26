package nl.ijmker.test.action.oauth2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.OAuth20Service;

import nl.ijmker.test.action.ExternalServerAction;
import nl.ijmker.test.scribejava.api.ResourceServerAPI20;
import nl.ijmker.test.util.CSRFUtil;
import nl.ijmker.test.util.ConfigUtil;
import nl.ijmker.test.util.URLUtil;

public class OAuth2GetAuthorizationCode extends ExternalServerAction {

	private static final Logger LOG = LoggerFactory.getLogger(OAuth2GetAuthorizationCode.class);

	/**
	 * @return
	 */
	public static OAuth2GetAuthorizationCode getInstance() {
		return new OAuth2GetAuthorizationCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.Action#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LOG.info("resourceServer=" + getServerType());

		// Resource server dependent
		String clientKey = ConfigUtil.getClientKey(getServerType());
		ResourceServerAPI20 selectedAPI = new ResourceServerAPI20(getServerType());

		// Protocol dependent
		String callbackURL = URLUtil.getOAuth2CallbackURL(request, getServerType());

		// Transaction dependent
		String newCSRFToken = CSRFUtil.getCSRFToken(request);

		LOG.info("clientKey=" + clientKey);
		LOG.info("callBackURL=" + callbackURL);

		OAuth20Service service = new ServiceBuilder().apiKey(clientKey).callback(callbackURL).state(newCSRFToken)
				.responseType(OAuthConstants.CODE).scope("r_basicprofile r_emailaddress").build(selectedAPI);

		// NB: No request token needed
		String authUrl = service.getAuthorizationUrl();

		LOG.info("authUrl=" + authUrl);

		response.sendRedirect(authUrl);
	}

}
