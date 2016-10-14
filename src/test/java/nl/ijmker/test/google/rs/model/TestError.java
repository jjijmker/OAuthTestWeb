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

public class TestError {

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

		File googleErrorFile = new File("src/test/resources/json/google_error.json");
		String googleError = FileUtils.readFileToString(googleErrorFile);

		GoogleErrorResponse errorResponse = mapper.readValue(googleError, GoogleErrorResponse.class);

		assertEquals(403, errorResponse.getError().getCode());
		assertEquals("Insufficient Permission", errorResponse.getError().getMessage());
		assertEquals(1, errorResponse.getError().getErrors().size());
		
		InnerError innerError = errorResponse.getError().getErrors().get(0);
		
		assertEquals("insufficientPermissions", innerError.getReason());
		assertEquals("Insufficient Permission", innerError.getMessage());
		assertEquals("global", innerError.getDomain());
	}
}
