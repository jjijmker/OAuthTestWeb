package nl.ijmker.test.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.ijmker.test.error.model.DisplayError;
import nl.ijmker.test.error.model.DisplayErrorOrigin;

public class DisplayErrorUtil {

	private static final Logger LOG = LoggerFactory.getLogger(DisplayErrorUtil.class);

	/**
	 * @param e
	 * @return
	 */
	public static DisplayError fromException(Throwable exception) {

		DisplayError error = new DisplayError();

		error.setOrigin(DisplayErrorOrigin.CLIENT);
		error.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		error.setMessage(ExceptionUtils.getMessage(exception));
		error.setStackTrace(ExceptionUtils.getStackTrace(exception));

		return error;
	}

	/**
	 * @param code
	 * @param description
	 * @param uri
	 * @return
	 */
	public static DisplayError fromCallbackError(String code, String description, String uri) {

		DisplayError error = new DisplayError();

		error.setOrigin(DisplayErrorOrigin.AUTHORISATION_SERVER);
		error.setStatusCode(Response.Status.ACCEPTED.getStatusCode());
		error.setMessage(description);
		error.setExtraInfo(uri);

		return error;
	}

	/**
	 * @param request
	 * @return
	 */
	public static DisplayError reportCallbackFailure(HttpServletRequest request) {

		String message = "Could not process callback. Parameters: " + getParameterString(request);

		DisplayError error = new DisplayError();

		error.setOrigin(DisplayErrorOrigin.CLIENT);
		error.setStatusCode(Response.Status.ACCEPTED.getStatusCode());
		error.setMessage(message);

		LOG.error(message);

		return error;
	}

	/**
	 * @param request
	 * @return
	 */
	public static DisplayError reportCommandFailure(HttpServletRequest request) {

		String message = "Could not determine command. Path Info: " + request.getPathInfo();

		DisplayError error = new DisplayError();

		error.setOrigin(DisplayErrorOrigin.CLIENT);
		error.setStatusCode(Response.Status.ACCEPTED.getStatusCode());
		error.setMessage(message);

		LOG.error(message);

		return error;
	}

	/**
	 * @param request
	 * @return
	 */
	private static String getParameterString(HttpServletRequest request) {

		StringBuilder messageBuilder = new StringBuilder();

		messageBuilder.append("Could not process callback. Parameters: ");

		Enumeration<String> paramEnum = request.getParameterNames();
		String param;

		while (paramEnum.hasMoreElements()) {

			param = paramEnum.nextElement();
			messageBuilder.append(param + "=" + request.getParameter(param));

			if (paramEnum.hasMoreElements()) {
				messageBuilder.append(", ");
			}
		}

		return messageBuilder.toString();
	}
}
