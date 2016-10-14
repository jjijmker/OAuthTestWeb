package nl.ijmker.test.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.scribejava.core.model.OAuth2AccessToken;

import nl.ijmker.test.rs.filter.LoggingFilter;
import nl.ijmker.test.util.ConfigUtil;
import nl.ijmker.test.util.SessionAttrUtil;
import nl.ijmker.test.util.URLUtil;

/**
 * @author Jan IJmker
 * 
 *         This command is specific to a certain server and action.
 *
 */
public abstract class ResourceCommand implements Command {

	private static final Logger LOG = LoggerFactory.getLogger(ResourceCommand.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		OAuth2AccessToken accessToken = SessionAttrUtil.getOAuth2AccessToken(request);

		LOG.info("Using accessToken: " + accessToken.getAccessToken());

		Client client = ClientBuilder.newClient();
		client.register(new LoggingFilter());
		WebTarget target = client.target(getResourceURL());
		Builder builder = target.request(getMediaType()).header(HttpHeaders.AUTHORIZATION,
				"Bearer " + accessToken.getAccessToken());
		Response responseAPI = builder.get();

		if (responseAPI.getStatus() != 200) {
			LOG.info("HTTP Status: " + responseAPI.getStatus());
			handleHTTPError(request, response, responseAPI);
			return;
		}

		Object responseObject = getResponseObject(responseAPI);

		LOG.info("Response: " + responseObject);

		// Store response in session
		SessionAttrUtil.storeResponse(request, responseObject);
		
		// Redirect to results page
		String resultsJSPURL = URLUtil.getExternalActionResultsJSPPath(request, getServer(), getResourceAction());
		response.sendRedirect(resultsJSPURL);
	}

	/**
	 * @return
	 */
	public abstract String getServer();

	/**
	 * @return
	 */
	public abstract String getResourceAction();

	/**
	 * @param response
	 */
	public abstract void handleHTTPError(HttpServletRequest request, HttpServletResponse response, Response responseAPI)
			throws IOException;

	/**
	 * @param response
	 * @return
	 */
	public abstract Object getResponseObject(Response response) throws Exception;

	/**
	 * @return
	 */
	public String getResourceURL() {

		return ConfigUtil.getResourceURL(getServer(), getResourceAction());
	}

	/**
	 * @return
	 */
	public MediaType getMediaType() {

		return MediaType.APPLICATION_JSON_TYPE;
	}
}
