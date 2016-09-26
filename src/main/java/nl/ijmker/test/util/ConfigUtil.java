package nl.ijmker.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.ijmker.test.constant.ConfigConstants;

public class ConfigUtil {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);

	private static Properties props = null;

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getName(String resourceServer) {
		return getConfigProperties().getProperty(resourceServer + ConfigConstants.PROP_NAME_POSTFIX_NAME);
	}
	
	/**
	 * @param servletContext
	 * @return
	 */
	public static String getClientKey(String resourceServer) {
		return getConfigProperties().getProperty(resourceServer + ConfigConstants.PROP_NAME_POSTFIX_CLIENT_KEY);
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getClientSecret(String resourceServer) {
		return getConfigProperties().getProperty(resourceServer + ConfigConstants.PROP_NAME_POSTFIX_CLIENT_SECRET);
	}
	
	/**
	 * @param servletContext
	 * @return
	 */
	public static String getAuth1AuthorizationURL(String resourceServer) {
		return getConfigProperties().getProperty(resourceServer + ConfigConstants.PROP_NAME_POSTFIX_URL_OAUTH1_AUTHORIZATION);
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getOAuth1RequestTokenURL(String resourceServer) {
		return getConfigProperties().getProperty(resourceServer + ConfigConstants.PROP_NAME_POSTFIX_URL_OAUTH1_REQUEST_TOKEN);
	}
	
	/**
	 * @param servletContext
	 * @return
	 */
	public static String getOAuth1AccessTokenURL(String resourceServer) {
		return getConfigProperties().getProperty(resourceServer + ConfigConstants.PROP_NAME_POSTFIX_URL_OAUTH1_ACCESS_TOKEN);
	}
	
	/**
	 * @param servletContext
	 * @return
	 */
	public static String getOAuth2AuthorizationURL(String resourceServer) {
		return getConfigProperties().getProperty(resourceServer + ConfigConstants.PROP_NAME_POSTFIX_URL_OAUTH2_AUTHORIZATION);
	}
	
	/**
	 * @param servletContext
	 * @return
	 */
	public static String getOAuth2AccessTokenURL(String resourceServer) {
		return getConfigProperties().getProperty(resourceServer + ConfigConstants.PROP_NAME_POSTFIX_URL_OAUTH2_ACCESS_TOKEN);
	}

	/**
	 * @param servletContext
	 * @return
	 */
	public static String getResourcesURL(String resourceServer) {
		return getConfigProperties().getProperty(resourceServer + ConfigConstants.PROP_NAME_POSTFIX_URL_RESOURCES);
	}

	/**
	 * @return
	 */
	public static Set<String> getResourceServers() {

		String key, resourceServer = null;
		String[] keyComponents = null;
		Set<String> resourceServers = new HashSet<String>();

		for (Object keyObject : getConfigProperties().keySet()) {

			key = (String) keyObject;
			keyComponents = key.split("\\.");

			if (keyComponents.length > 0) {
				resourceServer = keyComponents[0];
				resourceServers.add(resourceServer);
			}
		}

		return resourceServers;
	}

	/**
	 * @param resourceServer
	 * @return
	 */
	public static Set<String> getResourceServerActions(String resourceServer) {

		if (StringUtils.isEmpty(resourceServer) || resourceServer.equals("empty")) {
			return new HashSet<String>();
		}

		String support = getConfigProperties().getProperty(resourceServer + ConfigConstants.PROP_NAME_POSTFIX_SUPPORT);
		String[] supportComponents = support.split("\\.");

		return new HashSet<String>(Arrays.asList(supportComponents));
	}

	/**
	 * @param servletContext
	 * @return
	 */
	private static Properties getConfigProperties() {

		if (props == null) {
			try {
				LOG.info("Loading config properties from " + ConfigConstants.FILE_PATH_CONFIG_PROPS);

				props = new Properties();
				InputStream propsStream = ConfigUtil.class.getResourceAsStream(ConfigConstants.FILE_PATH_CONFIG_PROPS);
				props.load(propsStream);

				LOG.info("Loaded {} properties from " + ConfigConstants.FILE_PATH_CONFIG_PROPS, props.size());

			} catch (IOException e) {
				LOG.error("Could not open config properties from " + ConfigConstants.FILE_PATH_CONFIG_PROPS, e);
				throw new RuntimeException("Could not open config properties", e);
			}
		}

		return props;
	}
}
