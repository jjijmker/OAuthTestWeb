package nl.ijmker.test.action.oauth2;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.scribejava.core.model.OAuthConstants;

import nl.ijmker.test.action.ExternalServerAction;
import nl.ijmker.test.constant.ActionConstants;
import nl.ijmker.test.constant.ErrorConstants;
import nl.ijmker.test.util.CSRFUtil;
import nl.ijmker.test.util.ParamUtil;
import nl.ijmker.test.util.URLUtil;

public class OAuth2Callback extends ExternalServerAction {

	private static final Logger LOG = LoggerFactory.getLogger(OAuth2Callback.class);

	/**
	 * @return
	 */
	public static OAuth2Callback getInstance() {
		return new OAuth2Callback();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.Action#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String receivedCSRFToken = ParamUtil.getRequired(request, OAuthConstants.STATE);

		CSRFUtil.checkCSRFToken(request, receivedCSRFToken);

		if (ParamUtil.has(request, OAuthConstants.CODE)) {

			String actionPath = URLUtil.getInternalActionPath(request, getServerType(), ActionConstants.ACTION_OAUTH2_ACCESS_TOKEN_FROM_AUTHORIZATION_CODE);
			
			LOG.error("Forwarding to: " + actionPath);

			// Forward to next step in process
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(actionPath);
			requestDispatcher.forward(request, response);

		} else {

			LOG.error("Cannot determine callback result");

			String errorPage = URLUtil.getInternalErrorJSPPath(request,
					ErrorConstants.ERROR_CALLBACK_MISSING_PARAMETER);

			// Forward to error page
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(errorPage);
			requestDispatcher.forward(request, response);

		}
	}
}
