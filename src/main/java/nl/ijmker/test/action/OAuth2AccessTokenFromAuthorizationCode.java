package nl.ijmker.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.oauth.OAuth20Service;

import nl.ijmker.test.action.GenericCommand;
import nl.ijmker.test.constant.PageConstants;
import nl.ijmker.test.scribejava.api.ResourceServerAPI20;
import nl.ijmker.test.util.CSRFUtil;
import nl.ijmker.test.util.ConfigUtil;
import nl.ijmker.test.util.ParamUtil;
import nl.ijmker.test.util.SessionAttrUtil;
import nl.ijmker.test.util.URLUtil;

public class OAuth2AccessTokenFromAuthorizationCode extends GenericCommand {

	private static final Logger LOG = LoggerFactory.getLogger(OAuth2AccessTokenFromAuthorizationCode.class);

	/**
	 * @return
	 */
	public static OAuth2AccessTokenFromAuthorizationCode getInstance() {
		return new OAuth2AccessTokenFromAuthorizationCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.Action#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Parameters
		String receivedCSRFToken = ParamUtil.getRequired(request, OAuthConstants.STATE);

		if (!CSRFUtil.checkCSRFToken(request, receivedCSRFToken)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		String authorizationCode = ParamUtil.getRequired(request, OAuthConstants.CODE);

		// Server dependent
		String clientKey = ConfigUtil.getClientKey(getServer());
		String clientSecret = ConfigUtil.getClientSecret(getServer());
		ResourceServerAPI20 selectedAPI = new ResourceServerAPI20(getServer());

		// Protocol and server dependent
		String callbackURL = URLUtil.getCallbackURL(request, getServer());

		// Transaction dependent
		String newCSRFToken = CSRFUtil.getCSRFToken(request);

		OAuth20Service service = new ServiceBuilder().apiKey(clientKey).apiSecret(clientSecret).callback(callbackURL)
				.state(newCSRFToken).build(selectedAPI);

		OAuth2AccessToken accessToken = service.getAccessToken(authorizationCode);

		LOG.info("accessToken=" + accessToken);

		SessionAttrUtil.storeOAuth2AccessToken(request, accessToken);
		SessionAttrUtil.storeServer(request, getServer());

		response.sendRedirect(URLUtil.getExternalJSPPath(request, PageConstants.PAGE_STATUS, false));
	}

}
