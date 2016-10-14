package nl.ijmker.test.error.model;

public enum DisplayErrorOrigin {

	CLIENT, RESOURCE_SERVER, AUTHORISATION_SERVER, USER;

	public static DisplayErrorOrigin fromString(String originString) {
		return CLIENT;
	}
}