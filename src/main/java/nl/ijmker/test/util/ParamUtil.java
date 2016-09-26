package nl.ijmker.test.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import nl.ijmker.test.error.ParamMissingException;

public class ParamUtil {

	/**
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static boolean has(HttpServletRequest request, String paramName) {

		String paramValue = request.getParameter(paramName);

		return !StringUtils.isEmpty(paramValue) && !"empty".equals(paramValue);
	}

	/**
	 * @param request
	 * @param paramName
	 * @return
	 * @throws IllegalStateException
	 */
	public static String getRequired(HttpServletRequest request, String paramName) throws IllegalStateException {

		String paramValue = request.getParameter(paramName);

		if (StringUtils.isEmpty(paramValue)) {
			throw new ParamMissingException(paramName);
		}

		return paramValue;
	}
}
