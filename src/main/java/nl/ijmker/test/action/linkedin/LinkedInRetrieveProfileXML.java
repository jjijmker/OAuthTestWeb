package nl.ijmker.test.action.linkedin;

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

import nl.ijmker.test.action.LinkedInAction;
import nl.ijmker.test.constant.ServerTypeConstants;
import nl.ijmker.test.rs.filter.LoggingFilter;
import nl.ijmker.test.rs.model.linkedin.Person;
import nl.ijmker.test.util.ConfigUtil;
import nl.ijmker.test.util.SessionAttrUtil;
import nl.ijmker.test.util.URLUtil;

public class LinkedInRetrieveProfileXML extends LinkedInAction {

	private static final Logger LOG = LoggerFactory.getLogger(LinkedInRetrieveProfileXML.class);

	/**
	 * @return
	 */
	public static LinkedInRetrieveProfileXML getInstance() {
		return new LinkedInRetrieveProfileXML();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String linkedInBaseURL = ConfigUtil.getResourcesURL(ServerTypeConstants.SERVER_TYPE_LINKEDIN);
		OAuth2AccessToken accessToken = SessionAttrUtil.getOAuth2AccessToken(request);

		LOG.info("Using accessToken: " + accessToken.getAccessToken());

		Client client = ClientBuilder.newClient();
		client.register(new LoggingFilter());
		WebTarget target = client.target(linkedInBaseURL);
		Builder builder = target.request(MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION,
				"Bearer " + accessToken.getAccessToken());

		Response rsResponse = builder.get();

		int status = rsResponse.getStatus();
		Person person = rsResponse.readEntity(Person.class);

		LOG.info("Status: " + status);
		LOG.info("Person: " + person);

		if (person != null) {
			LOG.info("First Name: " + person.getFirstName());
			LOG.info("Last Name: " + person.getLastName());
			LOG.info("Headline: " + person.getHeadline());
			LOG.info("ID: " + person.getId());

			if (person.getSiteStandardProfileRequest() != null) {
				LOG.info("URL: " + person.getSiteStandardProfileRequest().getUrl());
			}
		}

		SessionAttrUtil.storeResponse(request, person);

		String resultsJSPURL = URLUtil.getExternalActionResultsJSPPath(request,
				ServerTypeConstants.SERVER_TYPE_LINKEDIN, LinkedInActionConstants.ACTION_LINKEDIN_RETRIEVE_XML);

		response.sendRedirect(resultsJSPURL);
	}
}
