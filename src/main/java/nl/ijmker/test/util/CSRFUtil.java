package nl.ijmker.test.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;

import nl.ijmker.test.constant.AlgoConstants;

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
	public static boolean checkCSRFToken(HttpServletRequest request, String receivedToken) {

		VarUtil.checkRequired(receivedToken);

		String storedToken = SessionAttrUtil.getCSRFToken(request);

		return receivedToken.equals(storedToken);
	}
}
