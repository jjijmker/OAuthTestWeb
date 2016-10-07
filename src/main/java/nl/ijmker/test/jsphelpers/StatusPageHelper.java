package nl.ijmker.test.jsphelpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.github.scribejava.core.model.OAuth2AccessToken;

import nl.ijmker.test.util.ConfigUtil;
import nl.ijmker.test.util.SessionAttrUtil;
import nl.ijmker.test.util.URLUtil;

public class StatusPageHelper {

	/**
	 * @param request
	 * @return
	 */
	public static String getServer(HttpServletRequest request) {

		String server = SessionAttrUtil.getServer(request);

		if (server != null) {
			return server;
		} else {
			return "NA";
		}
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getServerName(HttpServletRequest request) {

		String server = SessionAttrUtil.getServer(request);

		if (server != null) {
			return ConfigUtil.getName(server);
		} else {
			return "NA";
		}
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getOAuth2AccessToken(HttpServletRequest request) {

		OAuth2AccessToken accessToken = SessionAttrUtil.getOAuth2AccessToken(request);

		if (accessToken != null) {
			return accessToken.getAccessToken();
		}

		return "NA";
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getOAuth2Scope(HttpServletRequest request) {

		OAuth2AccessToken accessToken = SessionAttrUtil.getOAuth2AccessToken(request);

		if (accessToken != null) {
			return accessToken.getScope();
		}

		return "NA";
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getOAuth2TokenType(HttpServletRequest request) {

		OAuth2AccessToken accessToken = SessionAttrUtil.getOAuth2AccessToken(request);

		if (accessToken != null) {
			return accessToken.getTokenType();
		}

		return "NA";
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getOAuth2RefreshToken(HttpServletRequest request) {

		OAuth2AccessToken accessToken = SessionAttrUtil.getOAuth2AccessToken(request);

		if (accessToken != null) {
			return accessToken.getRefreshToken();
		}

		return "NA";
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getOAuth2Expiration(HttpServletRequest request) {

		OAuth2AccessToken accessToken = SessionAttrUtil.getOAuth2AccessToken(request);

		if (accessToken != null) {

			int expiresIn = accessToken.getExpiresIn();

			if (expiresIn > 0) {
				Date now = new Date();
				Calendar expirationCalendar = Calendar.getInstance();
				expirationCalendar.setTime(now);
				expirationCalendar.add(Calendar.SECOND, expiresIn);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

				return dateFormat.format(expirationCalendar.getTime());
			}
		}

		return "NA";
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getResourceName(HttpServletRequest request) {

		String server = SessionAttrUtil.getServer(request);
		String resource = SessionAttrUtil.getResource(request);

		return ConfigUtil.getResourceName(server, resource);
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getResourceURL(HttpServletRequest request) {

		String server = SessionAttrUtil.getServer(request);
		String resource = SessionAttrUtil.getResource(request);

		return URLUtil.getInternalActionPath(request, server, resource);
	}
}
