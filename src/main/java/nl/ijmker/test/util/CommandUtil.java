package nl.ijmker.test.util;

import java.util.Arrays;

import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.ijmker.test.action.Command;
import nl.ijmker.test.action.OAuthCommand;
import nl.ijmker.test.constant.ActionConstants;
import nl.ijmker.test.constant.PackageConstants;
import nl.ijmker.test.error.InvalidActionException;

/**
 * @author Jan IJmker
 * 
 *         nl.ijmker.test.action.generic.OAuth2AuthorizationCode
 */
public class CommandUtil {

	private static final Logger LOG = LoggerFactory.getLogger(CommandUtil.class);

	// Which actions are in the common package
	// These actions are the same regardless of server
	private static final String[] COMMON_ACTIONS = new String[] { ActionConstants.ACTION_PROCESS_ACTION_FORM,
			ActionConstants.ACTION_PROCESS_CALLBACK };

	// Which actions are in the generic package
	// These actions can be executed against various supporting servers
	private static final String[] GENERIC_ACTIONS = new String[] { ActionConstants.ACTION_OAUTH1_ACCESS_TOKEN,
			ActionConstants.ACTION_OAUTH1_VERIFIER, ActionConstants.ACTION_OAUTH2_ACCESS_TOKEN_APPLICATION_ONLY,
			ActionConstants.ACTION_OAUTH2_ACCESS_TOKEN_FROM_AUTHORIZATION_CODE,
			ActionConstants.ACTION_OAUTH2_ACCESS_TOKEN_FROM_USERNAME_PASSWORD,
			ActionConstants.ACTION_OAUTH2_ACCESS_TOKEN_IMPLICIT, ActionConstants.ACTION_OAUTH2_AUTHORIZATION_CODE };

	/**
	 * @param action
	 * @return
	 */
	public static Command getActionCommand(String action) {

		String fqcn = getFQCN(action);
		Command command = null;

		try {
			Class<?> classDefinition = Class.forName(fqcn);
			command = (Command) classDefinition.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			LOG.error("Could not create command from fqcn: " + fqcn, e);
		}

		if (command == null) {
			throw new InvalidActionException(
					"Action does not map to command: " + action + " (from fqcn: " + fqcn + ")");
		}

		return command;
	}

	/**
	 * @param server
	 * @param action
	 * @return
	 */
	public static Command getActionCommand(String server, String action) {

		String fqcn = getFQCN(server, action);
		Command command = null;

		try {
			Class<?> classDefinition = Class.forName(fqcn);
			command = (Command) classDefinition.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			LOG.error("Could not create command from fqcn: " + fqcn, e);
		}

		if (command == null) {
			throw new InvalidActionException(
					"Action does not map to command: " + action + " (from fqcn: " + fqcn + ")");
		}

		return command;
	}

	/**
	 * @param server
	 * @param resourceAction
	 * @param action
	 * @return
	 */
	public static Command getActionCommand(String server, String resourceAction, String action) {

		String fqcn = getFQCN(server, action);
		Command command = null;

		try {
			Class<?> classDefinition = Class.forName(fqcn);
			command = (Command) classDefinition.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			LOG.error("Could not create command from fqcn: " + fqcn, e);
		}

		if (command == null) {
			throw new InvalidActionException("Action does not map to command: " + action + " (fqcn: " + fqcn + ")");
		}

		if (command != null && command instanceof OAuthCommand) {
			// Set server and resource in generic command
			((OAuthCommand) command).setServer(server);
			((OAuthCommand) command).setResourceAction(resourceAction);
		}

		return command;
	}

	/*
	 * Utility
	 */

	/**
	 * @param action
	 * @return
	 */
	private static String getFQCN(String action) {

		return getCommandPackage(action) + PackageConstants.PACKAGE_SEP + getCommandClassName(action);
	}

	/**
	 * @param action
	 * @return
	 */
	private static String getFQCN(String server, String action) {

		return getCommandPackage(server, action) + PackageConstants.PACKAGE_SEP + getCommandClassName(action);
	}

	/**
	 * @param action
	 * @return
	 */
	private static String getCommandPackage(String action) {

		if (Arrays.asList(COMMON_ACTIONS).contains(action) || Arrays.asList(GENERIC_ACTIONS).contains(action)) {
			return PackageUtil.getDefaultActionPackage();
		}

		throw new InvalidActionException("Action does not map to common or generic command: " + action);
	}

	/**
	 * @param action
	 * @return
	 */
	private static String getCommandPackage(String server, String action) {

		if (Arrays.asList(COMMON_ACTIONS).contains(action) || Arrays.asList(GENERIC_ACTIONS).contains(action)) {
			return PackageUtil.getDefaultActionPackage();
		} else {
			return PackageUtil.getServerActionPackage(server);
		}
	}

	/**
	 * @param action
	 * @return
	 */
	private static String getCommandClassName(String action) {

		// Split action by "-"
		String[] actionComponents = action.split("-");

		// Initialize
		StringBuilder commandClassNameBuilder = new StringBuilder();

		// Add class name components
		for (String actionComponent : Arrays.asList(actionComponents)) {

			switch (actionComponent) {
			case "oauth1":
				commandClassNameBuilder.append("OAuth1");
				break;

			case "oauth2":
				commandClassNameBuilder.append("OAuth2");
				break;

			case "xml":
				commandClassNameBuilder.append("XML");
				break;

			case "json":
				commandClassNameBuilder.append("JSON");
				break;
			default:
				commandClassNameBuilder.append(WordUtils.capitalize(actionComponent));
				break;
			}
		}

		// Return result
		return commandClassNameBuilder.toString();
	}
}
