package nl.ijmker.test.constant;

public class ActionConstants {

	// Servlet mapping of action controller (servlet)
	public static final String ACTION_PREFIX = "/action/";
	public static final String ACTION_SEP = "/";

	// Common Actions
	public static final String ACTION_PROCESS_ACTION_FORM = "process-action-form";
	public static final String ACTION_PROCESS_ACTION_SWITCH = "process-action-switch";
	public static final String ACTION_PROCESS_CALLBACK = "process-callback";

	// Generic OAuth1 Actions
	public static final String ACTION_OAUTH1_CALLBACK = "oauth1-callback";
	public static final String ACTION_OAUTH1_VERIFIER = "oauth1-verifier";
	public static final String ACTION_OAUTH1_ACCESS_TOKEN = "oauth1-access-token";

	// Generic OAuth2 Actions
	public static final String ACTION_OAUTH2_AUTHORIZATION_CODE = "oauth2-authorization-code";
	public static final String ACTION_OAUTH2_ACCESS_TOKEN_FROM_AUTHORIZATION_CODE = "oauth2-access-token-from-authorization-code";
	public static final String ACTION_OAUTH2_ACCESS_TOKEN_FROM_USERNAME_PASSWORD = "oauth2-access-token-from-username-password";
	public static final String ACTION_OAUTH2_ACCESS_TOKEN_IMPLICIT = "oauth2-access-token-from-implicit";
	public static final String ACTION_OAUTH2_ACCESS_TOKEN_APPLICATION_ONLY = "oauth2-access-token-application-only";
}
