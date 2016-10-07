package nl.ijmker.test.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nl.ijmker.test.constant.ConfigConstants;
import nl.ijmker.test.constant.ServerConstants;

public class DottedPropertiesTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGet2() {

		String linkedInName = DottedProperties.get(ServerConstants.SERVER_LINKEDIN, ConfigConstants.PROP_NAME_NAME);

		assertEquals("LinkedIn", linkedInName);
	}

	@Test
	public void testGet3() {

		String linkedInName = DottedProperties.get(ServerConstants.SERVER_GOOGLE, ConfigConstants.PROP_NAME_CLIENT_KEY);

		assertEquals("493362059285-gevcevkq0gm9jvdcv72lrjpdlrtbbjd1.apps.googleusercontent.com", linkedInName);
	}

	@Test
	public void testList0() {

		Set<String> servers = DottedProperties.list();

		assertEquals(2, servers.size());
		assertTrue(servers.contains("linkedin"));
		assertTrue(servers.contains("google"));
	}

	@Test
	public void testList2() {

		Set<String> resources = DottedProperties.list(ServerConstants.SERVER_LINKEDIN,
				ConfigConstants.PROP_KEYWORD_RESOURCE);

		assertEquals(2, resources.size());
		assertTrue(resources.contains("profile-xml"));
		assertTrue(resources.contains("profile-json"));
	}
}
