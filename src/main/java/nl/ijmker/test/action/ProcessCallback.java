package nl.ijmker.test.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.scribejava.core.model.OAuthConstants;

import nl.ijmker.test.action.CommonCommand;
import nl.ijmker.test.constant.ActionConstants;
import nl.ijmker.test.constant.ErrorConstants;
import nl.ijmker.test.constant.PageConstants;
import nl.ijmker.test.constant.ParamConstants;
import nl.ijmker.test.util.CSRFUtil;
import nl.ijmker.test.util.ParamUtil;
import nl.ijmker.test.util.SessionAttrUtil;
import nl.ijmker.test.util.URLUtil;

public class ProcessCallback extends CommonCommand {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessCallback.class);

	/**
	 * @return
	 */
	public static ProcessCallback getInstance() {
		return new ProcessCallback();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.Action#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Check CSRF
		String receivedCSRFToken = ParamUtil.getRequired(request, OAuthConstants.STATE);

		if (!CSRFUtil.checkCSRFToken(request, receivedCSRFToken)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		// Handle Authorization Code
		if (ParamUtil.has(request, OAuthConstants.CODE)) {

			// Retrieve server and resource from session
			String server = SessionAttrUtil.getServer(request);
			String resource = SessionAttrUtil.getResource(request);

			// Determine action path for handling authorization code
			String actionPath = URLUtil.getInternalActionPath(request, server, resource,
					ActionConstants.ACTION_OAUTH2_ACCESS_TOKEN_FROM_AUTHORIZATION_CODE);

			LOG.info("Forwarding to: " + actionPath);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(actionPath);
			requestDispatcher.forward(request, response);
			return;

		}

		// Handle Returned Error
		if (ParamUtil.has(request, ParamConstants.PARAM_ERROR)) {

			String returnedError = ParamUtil.getRequired(request, ParamConstants.PARAM_ERROR);
			String returnedErrorDescription = ParamUtil.getRequired(request, ParamConstants.PARAM_ERROR_DESCRIPTION);
			String returnedErrorURI = ParamUtil.getRequired(request, ParamConstants.PARAM_ERROR_URI);

			SessionAttrUtil.storeError(request, returnedError);
			SessionAttrUtil.storeErrorDescription(request, returnedErrorDescription);
			SessionAttrUtil.storeErrorURI(request, returnedErrorURI);

			LOG.info(returnedError + ": " + returnedErrorDescription);

			String errorPageURL = URLUtil.getExternalJSPPath(request, PageConstants.PAGE_ERROR);
			response.sendRedirect(errorPageURL);
			return;
		}

		// Default Handling
		SessionAttrUtil.storeError(request, ErrorConstants.ERROR_CALLBACK_UNKNOWN_DESTINATION);
		SessionAttrUtil.storeErrorDescription(request, "Could not determine how to handle callback");

		LOG.error("Could not determine how to handle callback");

		String errorPageURL = URLUtil.getExternalJSPPath(request, PageConstants.PAGE_ERROR);
		response.sendRedirect(errorPageURL);
	}
}
