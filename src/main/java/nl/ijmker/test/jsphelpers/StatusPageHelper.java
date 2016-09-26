package nl.ijmker.test.jsphelpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.github.scribejava.core.model.OAuth2AccessToken;

import nl.ijmker.test.util.ConfigUtil;
import nl.ijmker.test.util.SessionAttrUtil;

public class StatusPageHelper {

	/**
	 * @param request
	 * @return
	 */
	public static String getServerType(HttpServletRequest request) {

		String serverType = SessionAttrUtil.getServerType(request);

		if (serverType != null) {
			return serverType;
		} else {
			return "NA";
		}
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getServerTypeName(HttpServletRequest request) {

		String serverType = SessionAttrUtil.getServerType(request);

		if (serverType != null) {
			return ConfigUtil.getName(serverType);
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
}
