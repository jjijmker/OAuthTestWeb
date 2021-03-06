package nl.ijmker.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.ijmker.test.action.OAuthCommand;

public class OAuth1Verifier extends OAuthCommand {

	private static final Logger LOG = LoggerFactory.getLogger(OAuth1Verifier.class);

	/**
	 * @return
	 */
	public static OAuth1Verifier getInstance() {
		return new OAuth1Verifier();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.Action#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LOG.info("Executing OAuth1 Init");

//		String apiKey = ConfigUtil.getLinkedInAPIKey();
//		String apiSecret = ConfigUtil.getLinkedInAPISecret();
//		String state = RandomStringUtils.random(AlgoConstants.CSRF_TOKEN_LENGTH);
//		String callBackUrl = URLUtil.getOAuth1CallbackURL(request);
//
//		OAuth10aService service = new ServiceBuilder().apiKey(apiKey).apiSecret(apiSecret).callback(callBackUrl)
//				.state(state).build(LinkedInApi.instance());
//
//		OAuth1RequestToken requestToken = service.getRequestToken();
//
//		LOG.info("requestToken=" + requestToken);
//
//		SessionAttrUtil.storeOAuth1RequestToken(request, requestToken);
//
//		String authUrl = service.getAuthorizationUrl(requestToken);
//
//		LOG.info("authUrl=" + authUrl);
//
//		response.sendRedirect(authUrl);
	}

}
