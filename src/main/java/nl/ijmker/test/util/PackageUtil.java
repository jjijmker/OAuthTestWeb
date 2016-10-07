package nl.ijmker.test.util;

import nl.ijmker.test.constant.PackageConstants;

public class PackageUtil {

	/**
	 * Returns the Java package a server specific command is defined in.
	 * 
	 * @param server
	 * @return
	 */
	public static String getServerActionPackage(String server) {

		return PackageConstants.PACKAGE_ACTION_PREFIX + PackageConstants.PACKAGE_SEP + server;
	}
}
