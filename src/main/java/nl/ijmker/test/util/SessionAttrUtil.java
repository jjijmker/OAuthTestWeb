package nl.ijmker.test.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth2AccessToken;

import nl.ijmker.test.constant.AttrConstants;
import nl.ijmker.test.error.model.DisplayError;
import nl.ijmker.test.linkedin.rs.model.Person;

public class SessionAttrUtil {

	/*
	 * Errors
	 */

	/**
	 * @param request
	 * @param displayError
	 */
	public static void storeDisplayError(HttpServletRequest request, DisplayError displayError) {

		store(request, AttrConstants.ATTR_DISPLAY_ERROR, displayError);
	}

	/*
	 * Server Type
	 */

	/**
	 * @param request
	 * @param response
	 */
	public static void storeServer(HttpServletRequest request, String server) {

		store(request, AttrConstants.ATTR_SERVER, server);
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getServer(HttpServletRequest request) {

		Object serverType = getRequired(request, AttrConstants.ATTR_SERVER);

		if (serverType != null) {
			return (String) serverType;
		}

		return null;
	}

	/*
	 * Resource Action
	 */

	/**
	 * @param request
	 * @param response
	 */
	public static void storeResourceAction(HttpServletRequest request, String resourceAction) {

		store(request, AttrConstants.ATTR_RESOURCE_ACTION, resourceAction);
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getResourceAction(HttpServletRequest request) {

		Object resource = getRequired(request, AttrConstants.ATTR_RESOURCE_ACTION);

		if (resource != null) {
			return (String) resource;
		}

		return null;
	}

	/*
	 * OAUth1
	 */

	/**
	 * @param request
	 * @param requestToken
	 */
	public static void storeOAuth1RequestToken(HttpServletRequest request, OAuth1RequestToken requestToken) {

		store(request, AttrConstants.ATTR_REQUEST_TOKEN, requestToken);
	}

	/**
	 * @param request
	 * @param accessToken
	 */
	public static void storeOAuth1AccessToken(HttpServletRequest request, OAuth1AccessToken accessToken) {

		store(request, AttrConstants.ATTR_ACCESS_TOKEN, accessToken);
	}

	/**
	 * @param request
	 * @return
	 */
	public static OAuth1RequestToken getOAuth1RequestToken(HttpServletRequest request) {

		Object requestToken = getRequired(request, AttrConstants.ATTR_REQUEST_TOKEN);

		if (requestToken != null) {
			return (OAuth1RequestToken) requestToken;
		}

		return null;
	}

	/**
	 * @param request
	 * @return
	 */
	public static OAuth1AccessToken getOAuth1AccessToken(HttpServletRequest request) {

		Object accessToken = getRequired(request, AttrConstants.ATTR_ACCESS_TOKEN);

		if (accessToken != null) {
			return (OAuth1AccessToken) accessToken;
		}

		return null;
	}

	/*
	 * OAUth2
	 */

	/**
	 * @param request
	 * @param accessToken
	 */
	public static void storeOAuth2AccessToken(HttpServletRequest request, OAuth2AccessToken accessToken) {

		store(request, AttrConstants.ATTR_ACCESS_TOKEN, accessToken);
	}

	/**
	 * @param request
	 * @return
	 */
	public static OAuth2AccessToken getOAuth2AccessToken(HttpServletRequest request) {

		Object accessToken = getRequired(request, AttrConstants.ATTR_ACCESS_TOKEN);

		if (accessToken != null) {
			return (OAuth2AccessToken) accessToken;
		}

		return null;
	}

	/*
	 * CSRF Token
	 */

	/**
	 * @param request
	 * @param newCSRFToken
	 */
	public static void storeCSRFToken(HttpServletRequest request, String newCSRFToken) {

		store(request, AttrConstants.ATTR_CSRF_TOKEN, newCSRFToken);
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getCSRFToken(HttpServletRequest request) {

		Object sessionCSRFToken = getRequired(request, AttrConstants.ATTR_CSRF_TOKEN);

		if (sessionCSRFToken != null) {
			return (String) sessionCSRFToken;
		}

		return null;
	}

	/*
	 * Response Object
	 */

	/**
	 * @param request
	 * @param serverType
	 */
	public static void storeResponse(HttpServletRequest request, Object object) {

		store(request, AttrConstants.ATTR_RESPONSE, object);
	}

	/**
	 * @param request
	 * @return
	 */
	public static Person getResponseLinkedInPerson(HttpServletRequest request) {

		Object serverType = getRequired(request, AttrConstants.ATTR_RESPONSE);

		if (serverType != null) {
			return (Person) serverType;
		}

		return null;
	}

	/*
	 * Utility
	 */

	/**
	 * @param request
	 * @param paramName
	 * @return
	 * @throws IllegalStateException
	 */
	private static Object getRequired(HttpServletRequest request, String attrName) throws IllegalStateException {

		HttpSession session = request.getSession(true);

		Object attrValue = session.getAttribute(attrName);

		if (attrValue == null) {
			throw new IllegalStateException("Session attribute '" + attrName + "' cannot be empty");
		}

		return attrValue;
	}

	/**
	 * @param request
	 * @param attrName
	 * @param value
	 */
	private static void store(HttpServletRequest request, String attrName, Object value) {

		HttpSession session = request.getSession(true);

		session.setAttribute(attrName, value);
	}
}
