package nl.ijmker.test.action.oauth1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.scribejava.core.model.OAuthConstants;

import nl.ijmker.test.action.ExternalServerAction;
import nl.ijmker.test.util.ParamUtil;

public class OAuth1GetAccessToken extends ExternalServerAction {

	private static final Logger LOG = LoggerFactory.getLogger(OAuth1GetAccessToken.class);

	/**
	 * @return
	 */
	public static OAuth1GetAccessToken getInstance() {
		return new OAuth1GetAccessToken();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.Action#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LOG.info("Executing OAuth1 Callback");

		String oAuthToken = ParamUtil.getRequired(request, OAuthConstants.TOKEN);
		String oAuthVerifier = ParamUtil.getRequired(request, OAuthConstants.VERIFIER);

		LOG.info("oAuthToken=" + oAuthToken + ", oAuthVerifier=" + oAuthVerifier);

//		OAuth1RequestToken requestToken = SessionAttrUtil.getOAuth1RequestToken(request);
//
//		String apiKey = ConfigUtil.get();
//		String apiSecret = ConfigUtil.getLinkedInAPISecret();
//		String state = RandomStringUtils.random(AlgoConstants.CSRF_TOKEN_LENGTH);
//
//		OAuth10aService service = new ServiceBuilder().apiKey(apiKey).apiSecret(apiSecret).state(state)
//				.build(LinkedInApi.instance());
//
//		OAuth1AccessToken accessToken = service.getAccessToken(requestToken, oAuthVerifier);
//
//		LOG.info("accessToken=" + accessToken);
//
//		SessionAttrUtil.storeOAuth1AccessToken(request, accessToken);

//		response.sendRedirect(URLUtil.getJSPPathOAuth1Authorised(request));
	}

}
