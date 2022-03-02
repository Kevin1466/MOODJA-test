package model;

import java.io.Serializable;
import java.util.Arrays;

import static model.Document.ACCEPTED;

/**
 * The email class
 */
public class Email implements Serializable {

	public static final String ASSIGNED = "assigned";

	private String[] recipients;
	private String subject;
	private String content;
	private String type;

	/**
	 * The constructor of Email.
	 */
	public Email() {

	}

	public Email(Document document, Employee employee) {
		setRecipient(employee.getEmail());
		setContent(document.getTitle(), employee.getFirstName() + " " + employee.getLastName(), document.getId(), document.getStatus());
		setSubject("Document Notification");
		type = document.getStatus();
	}

	public Email(Document document, Manager manager) {
		setRecipient(manager.getEmail());
		setContent(document.getTitle(), manager.getFirstName() + " " + manager.getLastName(), document.getId(), ASSIGNED);
		setSubject("Document Notification");
		type = ASSIGNED;
	}

	public String[] getRecipients() {
		return recipients;
	}

	public void setRecipients(String[] recipients) {
		this.recipients = recipients;
	}

	public void setRecipient(String recipient) {
		this.recipients = new String[1];
		recipients[0] = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String title, String name, int id, String messageType) {
		this.content = "<html><body>Dear" + name + ", <br/><br/>";
		this.content = "Document No. " + id + " " + title + " is " + messageType + ".";
		if (messageType.equals(ACCEPTED)) this.content += "Please login our system with your employee account to view the documents";
		this.content += "<br/>For further info, please check our system";
		this.content += "<br/><br/>Best regards, <br/>MOODJA" + "</body></html>";
	}

	@Override
	public String toString() {
		return "Recipients: " + Arrays.toString(recipients) + "; Type: " + type;
	}
}
