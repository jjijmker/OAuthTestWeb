package nl.ijmker.test.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.scribejava.core.model.OAuthConstants;

import nl.ijmker.test.constant.ActionConstants;
import nl.ijmker.test.constant.PageConstants;
import nl.ijmker.test.constant.ParamConstants;
import nl.ijmker.test.util.CSRFUtil;
import nl.ijmker.test.util.DisplayErrorUtil;
import nl.ijmker.test.util.ParamUtil;
import nl.ijmker.test.util.SessionAttrUtil;
import nl.ijmker.test.util.URLUtil;

public class ProcessCallback extends BaseCommand {

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

		// Handle authorization code
		if (ParamUtil.has(request, OAuthConstants.CODE)) {

			// Retrieve server and resource from session
			String server = SessionAttrUtil.getServer(request);
			String resourceAction = SessionAttrUtil.getResourceAction(request);

			// Determine action path for handling authorization code
			String actionPath = URLUtil.getInternalActionPath(request, server, resourceAction,
					ActionConstants.ACTION_OAUTH2_ACCESS_TOKEN_FROM_AUTHORIZATION_CODE);

			LOG.info("Forwarding to: " + actionPath);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(actionPath);
			requestDispatcher.forward(request, response);
			return;

		}

		// Handle Returned Error
		if (ParamUtil.has(request, ParamConstants.PARAM_ERROR)) {

			// Get parameter
			String code = ParamUtil.getRequired(request, ParamConstants.PARAM_ERROR);
			String description = ParamUtil.getRequired(request, ParamConstants.PARAM_ERROR_DESCRIPTION);
			String uri = ParamUtil.getRequired(request, ParamConstants.PARAM_ERROR_URI);

			// Create display error
			SessionAttrUtil.storeDisplayError(request, DisplayErrorUtil.fromCallbackError(code, description, uri));

			// Send redirect
			String errorPageURL = URLUtil.getExternalJSPPath(request, PageConstants.PAGE_ERROR);
			response.sendRedirect(errorPageURL);
			return;
		}

		// Create display error
		SessionAttrUtil.storeDisplayError(request, DisplayErrorUtil.reportCallbackFailure(request));
		String errorPageURL = URLUtil.getExternalJSPPath(request, PageConstants.PAGE_ERROR);
		response.sendRedirect(errorPageURL);
	}
}
