package nl.ijmker.test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.ijmker.test.constant.PageConstants;
import nl.ijmker.test.error.model.DisplayError;
import nl.ijmker.test.error.model.DisplayErrorOrigin;
import nl.ijmker.test.util.DisplayErrorUtil;
import nl.ijmker.test.util.SessionAttrUtil;
import nl.ijmker.test.util.URLUtil;

/**
 * @author Jan IJmker
 *
 */
@WebServlet(name = "OAuthErrorServlet", urlPatterns = { "/displayError" })
public class OAuthErrorServlet extends HttpServlet {

	private static final Logger LOG = LoggerFactory.getLogger(OAuthErrorServlet.class);

	private static final long serialVersionUID = 1L;

	private static final String ATTR_SERVLET_ERROR_STATUS_CODE = "javax.servlet.error.status_code";
	private static final String ATTR_SERVLET_ERROR_MESSAGE = "javax.servlet.error.message";
	private static final String ATTR_SERVLET_ERROR_EXCEPTION = "javax.servlet.error.exception";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DisplayError error;

		if (request.getAttribute(ATTR_SERVLET_ERROR_EXCEPTION) != null) {

			Throwable exception = (Throwable) request.getAttribute(ATTR_SERVLET_ERROR_EXCEPTION);
			Integer statusCode = (Integer) request.getAttribute(ATTR_SERVLET_ERROR_STATUS_CODE);
			String message = (String) request.getAttribute(ATTR_SERVLET_ERROR_MESSAGE);

			error = DisplayErrorUtil.fromException(exception);

			if (statusCode != null) {
				error.setStatusCode(statusCode);
			}

			if (message != null) {
				error.setMessage(message);
			}

			// Exception has already been logged...

		} else if (request.getAttribute(ATTR_SERVLET_ERROR_STATUS_CODE) != null) {

			int statusCode = (Integer) request.getAttribute(ATTR_SERVLET_ERROR_STATUS_CODE);
			String message = (String) request.getAttribute(ATTR_SERVLET_ERROR_MESSAGE);

			error = new DisplayError();

			error.setOrigin(DisplayErrorOrigin.CLIENT);
			error.setStatusCode(statusCode);
			error.setMessage(message);

			LOG.error("Status: " + statusCode + ", message: " + message);

		} else {

			error = new DisplayError();

			error.setOrigin(DisplayErrorOrigin.CLIENT);
			error.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			error.setMessage("Unknown");

			LOG.error("Unknown error");
		}

		// Store display error in session
		SessionAttrUtil.storeDisplayError(request, error);

		// Forward to error page
		String errorPageURL = URLUtil.getInternalJSPPath(request, PageConstants.PAGE_ERROR);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(errorPageURL);
		requestDispatcher.forward(request, response);
		return;
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
