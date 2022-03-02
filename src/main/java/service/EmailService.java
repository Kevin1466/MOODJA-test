package service;

import lombok.extern.slf4j.Slf4j;
import model.Document;
import model.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Email service for send emails.
 */
@Service
@Slf4j
public class EmailService {

	private static Logger logger = LogManager.getLogger(EmailService.class);

	@Resource
	private TaskExecutor taskExecutor;

	@Resource
	private JavaMailSender javaMailSender;

	private void sendEmail(final Email email){
		final MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("eric.wang@moodja.com");
			helper.setTo(email.getRecipients());
			helper.setSubject(email.getSubject());
			helper.setText(email.getContent(), true);
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					javaMailSender.send(message);
					logger.info("Email sent: " + email.toString());
				}
			});
		} catch (MessagingException messagingException) {
			logger.error("Email failed to send: " + email.toString());
			messagingException.printStackTrace();
		}
	}

	/**
	 * Notify employee the status of document.
	 * @param document
	 */
	public void notifyEmployee(Document document){
		Email email = new Email(document, document.getEmployee());
		sendEmail(email);
	}

	/**
	 * Nofity manager a new document can be viewed.
	 * @param document
	 */
	public void notifyManager(Document document){
		Email email = new Email(document, document.getManager());
		sendEmail(email);
	}
}
