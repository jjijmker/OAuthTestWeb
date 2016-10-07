package nl.ijmker.test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nl.ijmker.test.constant.ServerConstants;

public class ConfigUtilTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetNameGoogle() {

		assertEquals("Google", ConfigUtil.getName(ServerConstants.SERVER_GOOGLE));
	}

	@Test
	public void testGetNameLinkedIn() {

		assertEquals("LinkedIn", ConfigUtil.getName(ServerConstants.SERVER_LINKEDIN));
	}

	@Test
	public void testGetServers() {

		Set<String> servers = ConfigUtil.getServers();

		assertEquals(2, servers.size());
		assertTrue(servers.contains(ServerConstants.SERVER_GOOGLE));
		assertTrue(servers.contains(ServerConstants.SERVER_LINKEDIN));
	}

	@Test
	public void testGetResourcesGoogles() {

		Set<String> googleResources = ConfigUtil.getResources(ServerConstants.SERVER_GOOGLE);

		assertEquals(1, googleResources.size());
		assertTrue(googleResources.contains("gmail-profile"));
	}

	@Test
	public void testGetResourcesLinkedIn() {

		Set<String> linkedInResources = ConfigUtil.getResources(ServerConstants.SERVER_LINKEDIN);

		assertEquals(2, linkedInResources.size());
		assertTrue(linkedInResources.contains("profile-xml"));
		assertTrue(linkedInResources.contains("profile-json"));
	}

	@Test
	public void testGetResourceRequiredScopeGoogle() {

		Set<String> googleGmailProfileRequiredScopes = ConfigUtil
				.getResourceRequiredScope(ServerConstants.SERVER_GOOGLE, "gmail-profile");

		assertEquals(1, googleGmailProfileRequiredScopes.size());
		assertTrue(googleGmailProfileRequiredScopes.contains("profile"));
	}
	
	@Test
	public void testGetResourceRequiredScopeLinkedIn() {

		Set<String> linkedInProfile = ConfigUtil.getResourceRequiredScope(ServerConstants.SERVER_LINKEDIN, "profile-xml");

		assertEquals(2, linkedInProfile.size());
		assertTrue(linkedInProfile.contains("r_basicprofile"));
		assertTrue(linkedInProfile.contains("r_emailaddress"));
	}
}
