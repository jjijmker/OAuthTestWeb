package nl.ijmker.test.linkedin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import nl.ijmker.test.action.ResourceCommand;
import nl.ijmker.test.constant.PageConstants;
import nl.ijmker.test.constant.ServerConstants;
import nl.ijmker.test.util.URLUtil;

public abstract class LinkedInCommand extends ResourceCommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.ResourceCommand#getServer()
	 */
	@Override
	public String getServer() {

		return ServerConstants.SERVER_LINKEDIN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nl.ijmker.test.action.ResourceCommand#handleHTTPError(javax.ws.rs.core.
	 * Response)
	 */
	@Override
	public void handleHTTPError(HttpServletRequest request, HttpServletResponse response, Response responseAPI)
			throws IOException {

		// ObjectMapper mapper = new ObjectMapper();
		// String body = responseAPI.readEntity(String.class);
		// GoogleErrorResponse errorResponse = mapper.readValue(body,
		// GoogleErrorResponse.class);
		//
		// SessionAttrUtil.storeErrorStatusCode(request,
		// responseAPI.getStatus());
		// SessionAttrUtil.storeErrorDescription(request,
		// errorResponse.getError().getMessage());

		String errorPageURL = URLUtil.getExternalJSPPath(request, PageConstants.PAGE_ERROR);
		response.sendRedirect(errorPageURL);
	}
}
