package nl.ijmker.test.google.rs.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
public class Error {

	@XmlElement(name = "errors")
	private List<InnerError> errors = new ArrayList<InnerError>();

	@XmlElement
	private int code;

	@XmlElement
	private String message;

	public List<InnerError> getErrors() {
		return errors;
	}

	public void setErrors(List<InnerError> errors) {
		this.errors = errors;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
