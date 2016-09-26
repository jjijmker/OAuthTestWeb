package nl.ijmker.test.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;

import nl.ijmker.test.constant.AlgoConstants;
import nl.ijmker.test.error.CSRFException;

public class CSRFUtil {

	/**
	 * @param request
	 * @return
	 */
	public static String getCSRFToken(HttpServletRequest request) {

		String cSRFToken = RandomStringUtils.random(AlgoConstants.CSRF_TOKEN_LENGTH);

		SessionAttrUtil.storeCSRFToken(request, cSRFToken);

		return cSRFToken;
	}

	/**
	 * @param request
	 * @param receivedToken
	 */
	public static void checkCSRFToken(HttpServletRequest request, String receivedToken) {

		VarUtil.checkRequired(receivedToken);

		String storedToken = SessionAttrUtil.getCSRFToken(request);

		if (!receivedToken.equals(storedToken)) {
			throw new CSRFException(receivedToken, storedToken);
		}
	}

	/**
	 * @param request
	 * @param receivedToken
	 */
	public static String renewCSRFToken(HttpServletRequest request, String receivedToken) {

		// First check the received token
		checkCSRFToken(request, receivedToken);

		// If all good, return a new one
		return getCSRFToken(request);
	}
}
