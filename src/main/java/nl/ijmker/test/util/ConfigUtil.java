package nl.ijmker.test.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.ijmker.test.constant.ConfigConstants;
import nl.ijmker.test.properties.DottedProperties;

public class ConfigUtil {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);

	/*
	 * Property Values
	 */

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getServerName(String server) {
		return DottedProperties.get(server, ConfigConstants.PROP_NAME_NAME);
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getResourceActionName(String server, String resourceAction) {
		return DottedProperties.get(server, ConfigConstants.PROP_KEYWORD_RESOURCE, resourceAction,
				ConfigConstants.PROP_NAME_NAME);
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getSecurityActionName(String securityAction) {
		return DottedProperties.get(ConfigConstants.PROP_KEYWORD_SECURITY, securityAction,
				ConfigConstants.PROP_NAME_NAME);
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getClientKey(String server) {
		return DottedProperties.get(server, ConfigConstants.PROP_NAME_CLIENT_KEY);
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getClientSecret(String server) {
		return DottedProperties.get(server, ConfigConstants.PROP_NAME_CLIENT_SECRET);
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getAuth1AuthorizationURL(String server) {
		return DottedProperties.get(server, ConfigConstants.PROP_NAME_OAUTH1_URL_AUTHORIZATION);
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getOAuth1RequestTokenURL(String server) {
		return DottedProperties.get(server, ConfigConstants.PROP_NAME_OAUTH1_URL_REQUEST_TOKEN);
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getOAuth1AccessTokenURL(String server) {
		return DottedProperties.get(server, ConfigConstants.PROP_NAME_OAUTH1_URL_ACCESS_TOKEN);
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getOAuth2AuthorizationURL(String server) {
		return DottedProperties.get(server, ConfigConstants.PROP_NAME_OAUTH2_URL_AUTHORIZATION);
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getOAuth2AccessTokenURL(String server) {
		return DottedProperties.get(server, ConfigConstants.PROP_NAME_OAUTH2_URL_ACCESS_TOKEN);
	}

	/**
	 * @param server
	 * @param resourceAction
	 * @return
	 */
	public static Set<String> getResourceRequiredScope(String server, String resourceAction) {

		if (StringUtils.isEmpty(server) || server.equals("empty")) {
			return null;
		}

		// Get value
		String requiredScopes = DottedProperties.get(server, ConfigConstants.PROP_KEYWORD_RESOURCE, resourceAction,
				ConfigConstants.PROP_NAME_REQUIRED_SCOPES);

		// Convert to Set
		return new HashSet<String>(Arrays.asList(requiredScopes.split(",")));
	}

	/**
	 * @param server
	 * @param resourceAction
	 * @return
	 */
	public static String getResourceURL(String server, String resourceAction) {

		if (StringUtils.isEmpty(server) || server.equals("empty")) {
			return null;
		}

		return DottedProperties.get(server, ConfigConstants.PROP_KEYWORD_RESOURCE, resourceAction,
				ConfigConstants.PROP_NAME_URL);
	}

	/**
	 * @return
	 */
	public static Set<String> getServers() {

		Set<String> servers = DottedProperties.list();

		servers.remove(ConfigConstants.PROP_KEYWORD_SECURITY);

		return servers;
	}

	/**
	 * @param server
	 * @return
	 */
	public static Set<String> getSecurityActions(String server) {

		if (StringUtils.isEmpty(server) || server.equals("empty")) {
			return new HashSet<String>();
		}

		// Get value
		String actions = DottedProperties.get(server, ConfigConstants.PROP_NAME_OAUTH_SUPPORT);

		LOG.info("Value: " + actions);

		// Convert to Set
		return new HashSet<String>(Arrays.asList(actions.split("\\.")));
	}

	/**
	 * @return
	 */
	public static Set<String> getResourceActions(String server) {

		if (StringUtils.isEmpty(server) || server.equals("empty")) {
			return new HashSet<String>();
		}

		return DottedProperties.list(server, ConfigConstants.PROP_KEYWORD_RESOURCE);
	}
}
