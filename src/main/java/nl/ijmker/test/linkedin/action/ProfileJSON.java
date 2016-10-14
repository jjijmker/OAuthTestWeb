package nl.ijmker.test.linkedin.action;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import nl.ijmker.test.linkedin.constants.LinkedInActionConstants;
import nl.ijmker.test.linkedin.rs.model.Person;

public class ProfileJSON extends LinkedInCommand {

	private static final Logger LOG = LoggerFactory.getLogger(ProfileJSON.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.ResourceCommand#getResourceAction()
	 */
	@Override
	public String getResourceAction() {

		return LinkedInActionConstants.ACTION_PROFILE_JSON;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nl.ijmker.test.action.ResourceCommand#getResponseObject(javax.ws.rs.core.
	 * Response)
	 */
	@Override
	public Object getResponseObject(Response response) throws JsonParseException, JsonMappingException, IOException {

		String json = response.readEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();
		Person person = mapper.readValue(json, Person.class);

		LOG.info("First Name: " + person.getFirstName());
		LOG.info("Last Name: " + person.getLastName());
		LOG.info("Headline: " + person.getHeadline());
		LOG.info("ID: " + person.getId());

		if (person.getSiteStandardProfileRequest() != null) {
			LOG.info("URL: " + person.getSiteStandardProfileRequest().getUrl());
		}

		return person;
	}
}
