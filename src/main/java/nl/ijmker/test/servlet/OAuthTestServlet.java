package nl.ijmker.test.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.ijmker.test.action.Command;
import nl.ijmker.test.constant.ActionConstants;
import nl.ijmker.test.constant.PageConstants;
import nl.ijmker.test.error.InvalidActionException;
import nl.ijmker.test.util.CommandUtil;
import nl.ijmker.test.util.DisplayErrorUtil;
import nl.ijmker.test.util.SessionAttrUtil;
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

		Command command = null;

		LOG.info("actionPath: " + actionPath);

		if (actionPathComponents.length == 3) {
			// /server/resource/action
			String server = actionPathComponents[0];
			String resourceAction = actionPathComponents[1];
			String action = actionPathComponents[2];
			command = CommandUtil.getActionCommand(server, resourceAction, action);
		} else if (actionPathComponents.length == 2) {
			// /server/action
			String server = actionPathComponents[0];
			String action = actionPathComponents[1];
			command = CommandUtil.getActionCommand(server, action);
		} else if (actionPathComponents.length == 1) {
			// /action
			String action = actionPathComponents[0];
			command = CommandUtil.getActionCommand(action);
		} else {
			throw new InvalidActionException("Action URLs must have between 1 and 3 components");
		}

		// Check
		if (command == null) {
			// Create display error
			SessionAttrUtil.storeDisplayError(request, DisplayErrorUtil.reportCommandFailure(request));
			String errorPageURL = URLUtil.getExternalJSPPath(request, PageConstants.PAGE_ERROR);
			response.sendRedirect(errorPageURL);
			return;
		}

		LOG.info("command=" + command.getClass().getName() + " (super=" + command.getClass().getSuperclass() + ")");

		// Execute command
		try {
			command.execute(request, response);
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
