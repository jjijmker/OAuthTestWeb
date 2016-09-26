package nl.ijmker.test.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import nl.ijmker.test.constant.ActionConstants;
import nl.ijmker.test.constant.PageConstants;

public class URLUtil {

	/*
	 * JSP Paths
	 */

	/**
	 * @param request
	 * @param page
	 * @return
	 */
	public static String getInternalJSPPath(HttpServletRequest request, String page) {
		return page;
	}

	/**
	 * @param request
	 * @param page
	 * @return
	 */
	public static String getExternalJSPPath(HttpServletRequest request, String page, boolean copyParams) {

		StringBuilder pathBuilder = new StringBuilder(request.getContextPath());

		pathBuilder.append(page);

		if (copyParams) {
			Enumeration<String> paramNameEnum = request.getParameterNames();

			int paramNumber = 0;
			String paramName = null;

			while (paramNameEnum.hasMoreElements()) {
				paramName = paramNameEnum.nextElement();
				pathBuilder.append(paramName).append(paramNumber == 0 ? "?" : "&")
						.append(request.getParameter(paramName));
				paramNumber++;
			}
		}

		return pathBuilder.toString();
	}

	/**
	 * @param request
	 * @param page
	 * @return
	 */
	public static String getInternalErrorJSPPath(HttpServletRequest request, String errorCode) {

		return getInternalJSPPath(request, PageConstants.PAGE_ERROR + "?errorCode=" + errorCode);
	}

	/**
	 * @param request
	 * @param server
	 * @param action
	 * @return
	 */
	public static String getExternalActionResultsJSPPath(HttpServletRequest request, String server, String action) {

		String page = "/" + server + "/" + action + "-results.jsp";

		return getExternalJSPPath(request, page, false);
	}

	/*
	 * Action Paths
	 */

	/**
	 * @param request
	 * @param server
	 * @param action
	 * @return
	 */
	public static String getInternalActionPath(HttpServletRequest request, String server, String action) {

		return ActionConstants.ACTION_PREFIX + server + ActionConstants.ACTION_SEP + action;
	}

	/**
	 * @param request
	 * @param server
	 * @param action
	 * @return
	 */
	public static String getExternalActionPath(HttpServletRequest request, String server, String action) {

		return request.getContextPath() + ActionConstants.ACTION_PREFIX + server + ActionConstants.ACTION_SEP + action;
	}

	/**
	 * @param request
	 * @param action
	 * @return
	 */
	public static String getExternalActionPath(HttpServletRequest request, String action) {

		return request.getContextPath() + ActionConstants.ACTION_PREFIX + action;
	}

	/*
	 * Callbacks
	 */

	/**
	 * @param request
	 * @return
	 */
	public static String getOAuth2CallbackURL(HttpServletRequest request, String serverType) {

		// Determine callback path
		String callbackPath = getExternalActionPath(request, serverType, ActionConstants.ACTION_OAUTH2_CALLBACK);

		// Create absolute URL
		return getAbsoluteURL(request, callbackPath);
	}

	/*
	 * Form Processing
	 */

	/**
	 * @param request
	 * @return
	 */
	public static String getActionFormAction(HttpServletRequest request) {

		return getExternalActionPath(request, ActionConstants.ACTION_PROCESS_ACTION_FORM);
	}

	/*
	 * Utility
	 */

	/**
	 * @param request
	 * @param externalPath
	 * @return url
	 */
	private static String getAbsoluteURL(HttpServletRequest request, String externalPath) {

		StringBuilder urlBuilder = new StringBuilder(request.getScheme());

		urlBuilder.append("://").append(request.getServerName());

		if (request.getServerPort() != 80) {
			urlBuilder.append(":").append(request.getServerPort());
		}

		urlBuilder.append(externalPath);

		return urlBuilder.toString();
	}
}
