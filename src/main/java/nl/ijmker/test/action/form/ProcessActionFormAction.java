package nl.ijmker.test.action.form;

import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.ijmker.test.action.BaseAction;
import nl.ijmker.test.constant.ParamConstants;
import nl.ijmker.test.util.ParamUtil;
import nl.ijmker.test.util.URLUtil;

public class ProcessActionFormAction extends BaseAction {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessActionFormAction.class);

	/**
	 * @return
	 */
	public static ProcessActionFormAction getInstance() {
		return new ProcessActionFormAction();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.Action#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Enumeration<String> paramEnum = request.getParameterNames();
		String paramName = null;

		while (paramEnum.hasMoreElements()) {
			paramName = paramEnum.nextElement();
			LOG.info("paramName=" + paramName + " paramValue=" + request.getParameter(paramName));
		}

		// Read parameter
		boolean executeAction = Boolean.valueOf(ParamUtil.getRequired(request, ParamConstants.PARAM_EXECUTE_ACTION));

		if (executeAction && ParamUtil.has(request, ParamConstants.PARAM_RESOURCE_SERVER) && ParamUtil.has(request, ParamConstants.PARAM_RESOURCE_SERVER_ACTION)) {

			// Read parameters
			String resourceServer = ParamUtil.getRequired(request, ParamConstants.PARAM_RESOURCE_SERVER);
			String resourceServerAction = ParamUtil.getRequired(request, ParamConstants.PARAM_RESOURCE_SERVER_ACTION);

			// Determine action path
			String actionPath = URLUtil.getInternalActionPath(request, resourceServer, resourceServerAction);

			// Forward to action
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(actionPath);
			requestDispatcher.forward(request, response);

		} else {

			// Return to action form
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);

		}
	}
}
