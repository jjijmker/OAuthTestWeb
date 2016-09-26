package nl.ijmker.test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.ijmker.test.action.BaseAction;
import nl.ijmker.test.action.ExternalServerAction;
import nl.ijmker.test.action.form.ProcessActionFormAction;
import nl.ijmker.test.action.linkedin.LinkedInActionConstants;
import nl.ijmker.test.action.linkedin.LinkedInRetrieveProfileJSON;
import nl.ijmker.test.action.linkedin.LinkedInRetrieveProfileXML;
import nl.ijmker.test.action.oauth1.OAuth1GetAccessToken;
import nl.ijmker.test.action.oauth1.OAuth1GetVerifier;
import nl.ijmker.test.action.oauth2.OAuth2Callback;
import nl.ijmker.test.action.oauth2.OAuth2GetAccessTokenFromAuthorizationCode;
import nl.ijmker.test.action.oauth2.OAuth2GetAuthorizationCode;
import nl.ijmker.test.constant.ActionConstants;
import nl.ijmker.test.constant.ErrorConstants;
import nl.ijmker.test.util.URLUtil;

/**
 * @author jjijmker
 *
 */
@WebServlet(name = "OAuthTestServlet", urlPatterns = { ActionConstants.ACTION_PREFIX + "*" })
public class OAuthTestServlet extends HttpServlet {

	private static final Logger LOG = LoggerFactory.getLogger(OAuthTestServlet.class);

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Strip leading "/" from
		String actionPath = request.getPathInfo().substring(1);

		// Split in components separated by "/"
		String[] actionPathComponents = actionPath.split(ActionConstants.ACTION_SEP);

		String serverType = null, serverAction = null;
		BaseAction serverActionCommand = null;

		if (actionPathComponents.length > 1) {
			serverType = actionPathComponents[0];
			serverAction = actionPathComponents[1];
		} else {
			serverAction = actionPathComponents[0];
		}

		LOG.info("serverAction=" + serverAction + " serverType=" + serverType);

		switch (serverAction) {
		// Form Handling
		case ActionConstants.ACTION_PROCESS_ACTION_FORM:
			serverActionCommand = ProcessActionFormAction.getInstance();
			break;
		// OAuth1 Flows
		case ActionConstants.ACTION_OAUTH1_VERIFIER:
			serverActionCommand = OAuth1GetVerifier.getInstance();
			break;
		case ActionConstants.ACTION_OAUTH1_ACCESS_TOKEN:
			serverActionCommand = OAuth1GetAccessToken.getInstance();
			break;
		// OAuth2 Flows
		case ActionConstants.ACTION_OAUTH2_AUTHORIZATION_CODE:
			serverActionCommand = OAuth2GetAuthorizationCode.getInstance();
			break;
		case ActionConstants.ACTION_OAUTH2_CALLBACK:
			serverActionCommand = OAuth2Callback.getInstance();
			break;
		case ActionConstants.ACTION_OAUTH2_ACCESS_TOKEN_FROM_AUTHORIZATION_CODE:
			serverActionCommand = OAuth2GetAccessTokenFromAuthorizationCode.getInstance();
			break;
		// Resources Calls
		case LinkedInActionConstants.ACTION_LINKEDIN_RETRIEVE_XML:
			serverActionCommand = LinkedInRetrieveProfileXML.getInstance();
			break;
		case LinkedInActionConstants.ACTION_LINKEDIN_RETRIEVE_JSON:
			serverActionCommand = LinkedInRetrieveProfileJSON.getInstance();
			break;
		}

		if (serverActionCommand == null) {
			// Determine error path
			String errorJSPPath = URLUtil.getInternalErrorJSPPath(request, ErrorConstants.ERROR_INVALID_ACTION_PATH);

			// Forward to error page
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(errorJSPPath);
			requestDispatcher.forward(request, response);
		}

		if (serverActionCommand instanceof ExternalServerAction && serverType == null) {
			// Determine error path
			String errorJSPPath = URLUtil.getInternalErrorJSPPath(request,
					ErrorConstants.ERROR_EXTERNAL_SERVER_ACTION_WITHOUT_SERVER_TYPE);

			// Forward to error page
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(errorJSPPath);
			requestDispatcher.forward(request, response);
		}

		LOG.info("serverActionCommand=" + serverActionCommand.getClass().getName() + " (super="
				+ serverActionCommand.getClass().getSuperclass() + ")");

		if (serverActionCommand instanceof ExternalServerAction) {
			((ExternalServerAction) serverActionCommand).setServerType(serverType);
		}

		try {
			serverActionCommand.execute(request, response);
		} catch (Exception e) {
			throw new ServletException("Action processing error", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
