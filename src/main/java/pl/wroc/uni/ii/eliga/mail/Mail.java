package pl.wroc.uni.ii.eliga.mail;

public class Mail {
	private String contents;
	private String subject;
	private String recipientAddress;

	public Mail(String contents, String subject, String recipientAddress) {
		super();
		this.contents = contents;
		this.subject = subject;
		this.recipientAddress = recipientAddress;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

}
