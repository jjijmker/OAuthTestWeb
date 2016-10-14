package nl.ijmker.test.google.rs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GMailProfileResponse {

	@XmlElement
	private String emailAddress;

	@XmlElement
	private int messagesTotal;

	@XmlElement
	private int threadsTotal;

	@XmlElement
	private long historyId;

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getMessagesTotal() {
		return messagesTotal;
	}

	public void setMessagesTotal(int messagesTotal) {
		this.messagesTotal = messagesTotal;
	}

	public int getThreadsTotal() {
		return threadsTotal;
	}

	public void setThreadsTotal(int threadsTotal) {
		this.threadsTotal = threadsTotal;
	}

	public long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(long historyId) {
		this.historyId = historyId;
	}
}
