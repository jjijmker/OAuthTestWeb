package nl.ijmker.test.google.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.ijmker.test.action.ResourceCommand;
import nl.ijmker.test.constant.PageConstants;
import nl.ijmker.test.constant.ServerConstants;
import nl.ijmker.test.error.model.DisplayErrorOrigin;
import nl.ijmker.test.google.rs.model.GoogleErrorResponse;
import nl.ijmker.test.google.util.DisplayErrorUtil;
import nl.ijmker.test.util.SessionAttrUtil;
import nl.ijmker.test.util.URLUtil;

/**
 * @author jjijmker
 *
 */
public abstract class GoogleCommand extends ResourceCommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.ResourceCommand#getServer()
	 */
	@Override
	public String getServer() {

		return ServerConstants.SERVER_GOOGLE;
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

		ObjectMapper mapper = new ObjectMapper();
		String body = responseAPI.readEntity(String.class);
		GoogleErrorResponse errorResponse = mapper.readValue(body, GoogleErrorResponse.class);

		// Create display error
		SessionAttrUtil.storeDisplayError(request,
				DisplayErrorUtil.fromGoogleErrorResponse(errorResponse, DisplayErrorOrigin.RESOURCE_SERVER));
		String errorPageURL = URLUtil.getExternalJSPPath(request, PageConstants.PAGE_ERROR);
		response.sendRedirect(errorPageURL);
	}
}
