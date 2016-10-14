package nl.ijmker.test.google.rs.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestGMailProfile {

	ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		mapper = new ObjectMapper();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testError() throws JsonParseException, JsonMappingException, IOException {

		File googleProfileFile = new File("src/test/resources/json/google_gmail_profile.json");
		String googleProfile = FileUtils.readFileToString(googleProfileFile);

		GMailProfileResponse profileResponse = mapper.readValue(googleProfile, GMailProfileResponse.class);

		assertEquals("jan.ijmker@gmail.com", profileResponse.getEmailAddress());
		assertEquals(2375595, profileResponse.getHistoryId());
	}
}
