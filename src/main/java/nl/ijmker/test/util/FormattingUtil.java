package nl.ijmker.test.util;

import nl.ijmker.test.error.model.DisplayErrorOrigin;

public class FormattingUtil {

	/**
	 * @param origin
	 * @return
	 */
	public static String formatOrigin(DisplayErrorOrigin origin) {
		
		if (origin == null) {
			return null;
		}

		switch (origin) {
		case CLIENT:
			return "Client";
		case RESOURCE_SERVER:
			return "Resource Server";
		case AUTHORISATION_SERVER:
			return "Authorization Server";
		case USER:
			return "User";
		default:
			return "Unknown";
		}
	}
}
