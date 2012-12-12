package pl.wroc.uni.ii.eliga.mail;

public interface MailSender {
	void send(Mail mail) throws MailSendException;
}
