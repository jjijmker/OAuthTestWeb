package nl.ijmker.test.google.action;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.ijmker.test.google.constant.GoogleActionConstants;
import nl.ijmker.test.google.rs.model.GMailProfileResponse;

public class GmailProfile extends GoogleCommand {

	private static final Logger LOG = LoggerFactory.getLogger(GmailProfile.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.ResourceCommand#getResourceAction()
	 */
	@Override
	public String getResourceAction() {

		return GoogleActionConstants.ACTION_GMAIL_PROFILE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nl.ijmker.test.action.ResourceCommand#getResponseObject(javax.ws.rs.core.
	 * Response)
	 */
	@Override
	public Object getResponseObject(Response response) throws Exception {

		String body = response.readEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();
		GMailProfileResponse serviceResponse = mapper.readValue(body, GMailProfileResponse.class);

		LOG.info("Email Address: " + serviceResponse.getEmailAddress());
		LOG.info("Messages Total: " + serviceResponse.getMessagesTotal());
		LOG.info("Threads Total: " + serviceResponse.getThreadsTotal());
		LOG.info("History ID: " + serviceResponse.getHistoryId());

		return serviceResponse;
	}
}
