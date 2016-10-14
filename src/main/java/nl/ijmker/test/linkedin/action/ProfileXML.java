package nl.ijmker.test.linkedin.action;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.ijmker.test.linkedin.constants.LinkedInActionConstants;
import nl.ijmker.test.linkedin.rs.model.Person;

public class ProfileXML extends LinkedInCommand {

	private static final Logger LOG = LoggerFactory.getLogger(ProfileXML.class);

	/**
	 * @return
	 */
	public static ProfileXML getInstance() {
		return new ProfileXML();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.ijmker.test.action.ResourceCommand#getResourceAction()
	 */
	@Override
	public String getResourceAction() {

		return LinkedInActionConstants.ACTION_PROFILE_XML;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nl.ijmker.test.action.ResourceCommand#getResponseObject(javax.ws.rs.core.
	 * Response)
	 */
	@Override
	public Object getResponseObject(Response rsResponse) throws Exception {

		Person person = rsResponse.readEntity(Person.class);

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