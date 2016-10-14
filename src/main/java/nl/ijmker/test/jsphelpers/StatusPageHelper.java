package nl.ijmker.test.jsphelpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.github.scribejava.core.model.OAuth2AccessToken;

import nl.ijmker.test.constant.ActionConstants;
import nl.ijmker.test.util.ConfigUtil;
import nl.ijmker.test.util.SessionAttrUtil;
import nl.ijmker.test.util.URLUtil;

/**
 * @author jjijmker
 *
 */
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
			return ConfigUtil.getServerName(server);
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
	public static String getSelectedResourceActionName(HttpServletRequest request) {

		String server = SessionAttrUtil.getServer(request);
		String resourceAction = SessionAttrUtil.getResourceAction(request);

		return ConfigUtil.getResourceActionName(server, resourceAction);
	}

	/**
	 * @param request
	 * @param resourceAction
	 * @return
	 */
	public static String getResourceActionName(HttpServletRequest request, String resourceAction) {

		String server = SessionAttrUtil.getServer(request);

		return ConfigUtil.getResourceActionName(server, resourceAction);
	}

	/**
	 * @param request
	 * @return
	 */
	public static Set<String> getOtherResourceActions(HttpServletRequest request) {

		String server = SessionAttrUtil.getServer(request);
		Set<String> resourceActions = ConfigUtil.getResourceActions(server);

		String selectedResourceAction = SessionAttrUtil.getResourceAction(request);
		resourceActions.remove(selectedResourceAction);

		return resourceActions;
	}

	/**
	 * @param request
	 * @return
	 */
	public static String getSelectedResourceActionURL(HttpServletRequest request) {

		String server = SessionAttrUtil.getServer(request);
		String resourceAction = SessionAttrUtil.getResourceAction(request);

		return URLUtil.getInternalActionPath(request, server, resourceAction);
	}

	/**
	 * @param request
	 * @param resourceAction
	 * @return
	 */
	public static String getResourceActionSwitchURL(HttpServletRequest request, String resourceAction) {

		String server = SessionAttrUtil.getServer(request);

		return URLUtil.getInternalActionPath(request, server, resourceAction,
				ActionConstants.ACTION_PROCESS_ACTION_SWITCH);
	}
}
