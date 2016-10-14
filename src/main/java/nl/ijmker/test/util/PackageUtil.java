package nl.ijmker.test.util;

import nl.ijmker.test.constant.PackageConstants;

public class PackageUtil {

	/**
	 * Returns the Java package non-server commands are defined in.
	 * 
	 * @param server
	 * @return
	 */
	public static String getDefaultActionPackage() {

		return PackageConstants.PACKAGE_BASE + PackageConstants.PACKAGE_SEP + PackageConstants.PACKAGE_ACTION;
	}

	/**
	 * Returns the Java package server-specific commands are defined in.
	 * 
	 * @param server
	 * @return
	 */
	public static String getServerActionPackage(String server) {

		return PackageConstants.PACKAGE_BASE + PackageConstants.PACKAGE_SEP + server + PackageConstants.PACKAGE_SEP + PackageConstants.PACKAGE_ACTION;
	}
}
