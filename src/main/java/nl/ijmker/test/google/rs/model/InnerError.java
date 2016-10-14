package nl.ijmker.test.google.rs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = ".")
@XmlAccessorType(XmlAccessType.FIELD)
public class InnerError {

	@XmlElement
	private String domain;

	@XmlElement
	private String reason;

	@XmlElement
	private String message;

	@XmlElement
	private String extendedHelp;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getExtendedHelp() {
		return extendedHelp;
	}

	public void setExtendedHelp(String extendedHelp) {
		this.extendedHelp = extendedHelp;
	}
}
