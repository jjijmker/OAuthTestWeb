package nl.ijmker.test.action.linkedin.jsphelpers;

import javax.servlet.http.HttpServletRequest;

import nl.ijmker.test.action.linkedin.LinkedInActionConstants;
import nl.ijmker.test.constant.ServerTypeConstants;
import nl.ijmker.test.util.URLUtil;

public class ActionsPageHelper {

	/**
	 * @param request
	 * @return
	 */
	public static String getRetrieveProfileXMLURL(HttpServletRequest request) {

		return URLUtil.getExternalActionPath(request, ServerTypeConstants.SERVER_TYPE_LINKEDIN,
				LinkedInActionConstants.ACTION_LINKEDIN_RETRIEVE_XML);
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getRetrieveProfileJSONURL(HttpServletRequest request) {

		return URLUtil.getExternalActionPath(request, ServerTypeConstants.SERVER_TYPE_LINKEDIN,
				LinkedInActionConstants.ACTION_LINKEDIN_RETRIEVE_JSON);
	}
}
