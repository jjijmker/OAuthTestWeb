package nl.ijmker.test.linkedin.rs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

	@XmlElement
	private String id;

	@XmlElement(name = "first-name")
	private String firstName;

	@XmlElement(name = "last-name")
	private String lastName;

	@XmlElement
	private String headline;

	@XmlElement(name = "site-standard-profile-request")
	private SiteStandardProfileRequest siteStandardProfileRequest;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public SiteStandardProfileRequest getSiteStandardProfileRequest() {
		return siteStandardProfileRequest;
	}

	public void setSiteStandardProfileRequest(SiteStandardProfileRequest siteStandardProfileRequest) {
		this.siteStandardProfileRequest = siteStandardProfileRequest;
	}
}
