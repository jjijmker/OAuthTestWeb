package nl.ijmker.test.google.util;

import java.util.List;

import nl.ijmker.test.error.model.DisplayError;
import nl.ijmker.test.error.model.DisplayErrorOrigin;
import nl.ijmker.test.google.rs.model.GoogleErrorResponse;
import nl.ijmker.test.google.rs.model.InnerError;

public class DisplayErrorUtil {

	/**
	 * @param e
	 * @return
	 */
	public static DisplayError fromGoogleErrorResponse(GoogleErrorResponse response, DisplayErrorOrigin origin) {

		DisplayError error = new DisplayError();

		error.setOrigin(origin);
		error.setStatusCode(response.getError().getCode());
		error.setMessage(response.getError().getMessage());

		List<InnerError> innerErrors = response.getError().getErrors();

		if (innerErrors.isEmpty()) {
			InnerError innerError = innerErrors.get(0);
			error.setExtraInfo(innerError.getExtendedHelp());
		}

		return error;
	}
}
