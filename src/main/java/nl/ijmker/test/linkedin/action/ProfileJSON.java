package nl.ijmker.test.linkedin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;

import nl.ijmker.test.action.SpecificCommand;
import nl.ijmker.test.constant.ServerConstants;
import nl.ijmker.test.linkedin.constants.LinkedInActionConstants;
import nl.ijmker.test.linkedin.rs.model.Person;
import nl.ijmker.test.rs.filter.LoggingFilter;
import nl.ijmker.test.util.ConfigUtil;
import nl.ijmker.test.util.SessionAttrUtil;
import nl.ijmker.test.util.URLUtil;

public class ProfileJSON extends SpecificCommand {

	private static final Logger LOG = LoggerFactory.getLogger(ProfileJSON.class);

	/**
	 * @return
	 */
	public static ProfileJSON getInstance() {
		return new ProfileJSON();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String resourceURL = ConfigUtil.getResourceURL(ServerConstants.SERVER_LINKEDIN,
				LinkedInActionConstants.ACTION_PROFILE_JSON);
		OAuth2AccessToken accessToken = SessionAttrUtil.getOAuth2AccessToken(request);

		LOG.info("Using accessToken: " + accessToken.getAccessToken());

		Client client = ClientBuilder.newClient();
		client.register(new LoggingFilter());
		WebTarget target = client.target(resourceURL);
		Builder builder = target.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken.getAccessToken())
				.header("x-li-format", "json");

		Response rsResponse = builder.get();

		int status = rsResponse.getStatus();
		String json = rsResponse.readEntity(String.class);

		ObjectMapper mapper = new ObjectMapper();
		Person person = mapper.readValue(json, Person.class);

		LOG.info("Status: " + status);
		LOG.info("JSON: " + json);
		LOG.info("firstName: " + person.getFirstName());

		SessionAttrUtil.storeResponse(request, person);

		String resultsJSPURL = URLUtil.getExternalActionResultsJSPPath(request, ServerConstants.SERVER_LINKEDIN,
				LinkedInActionConstants.ACTION_PROFILE_JSON);

		response.sendRedirect(resultsJSPURL);
	}
}
