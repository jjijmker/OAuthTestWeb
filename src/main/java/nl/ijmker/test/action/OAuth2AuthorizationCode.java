package nl.ijmker.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.OAuth20Service;

import nl.ijmker.test.scribejava.api.ResourceServerAPI20;
import nl.ijmker.test.util.CSRFUtil;
import nl.ijmker.test.util.ConfigUtil;
import nl.ijmker.test.util.SessionAttrUtil;
import nl.ijmker.test.util.URLUtil;

public class OAuth2AuthorizationCode extends OAuthCommand {

	private static final Logger LOG = LoggerFactory.getLogger(OAuth2AuthorizationCode.class);

	/**
	 * @return
	 */
	public static OAuth2AuthorizationCode getInstance() {
		return new OAuth2AuthorizationCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.Action#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LOG.info("server=" + getServer());
		LOG.info("resource=" + getResourceAction());

		// Resource server dependent
		String clientKey = ConfigUtil.getClientKey(getServer());
		ResourceServerAPI20 selectedAPI = new ResourceServerAPI20(getServer());

		// Resource Action dependent
		// Set<String> requiredScopeSet = ConfigUtil.getResourceRequiredScope(getServer(), getResourceAction());
		String requiredScope = "openid email"; //StringUtils.join(requiredScopeSet, " ");

		// Protocol dependent
		String callbackURL = URLUtil.getCallbackURL(request, getServer());

		// Transaction dependent
		String newCSRFToken = CSRFUtil.getCSRFToken(request);

		LOG.info("clientKey=" + clientKey);
		LOG.info("requiredScope=" + requiredScope);
		LOG.info("callBackURL=" + callbackURL);
		LOG.info("requiredScope=" + requiredScope);

		OAuth20Service service = new ServiceBuilder().apiKey(clientKey).callback(callbackURL).state(newCSRFToken)
				.responseType(OAuthConstants.CODE).scope(requiredScope).build(selectedAPI);
		
		// (arg0);

		// NB: No request token needed
		String authUrl = service.getAuthorizationUrl();

		LOG.info("authUrl=" + authUrl);

		SessionAttrUtil.storeServer(request, getServer());
		SessionAttrUtil.storeResourceAction(request, getResourceAction());

		response.sendRedirect(authUrl);
	}

}
