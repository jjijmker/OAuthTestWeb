package nl.ijmker.test.util;

import nl.ijmker.test.error.ValueMissingException;

public class VarUtil {

	/**
	 * @param value
	 */
	public static void checkRequired(Object value) {

		if (value == null) {
			throw new ValueMissingException();
		}
	}

	/**
	 * @param value
	 */
	public static void checkNonEmpty(String value) {

		if (value == null || value.isEmpty()) {
			throw new ValueMissingException();
		}
	}
}
