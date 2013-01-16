package pl.wroc.uni.ii.eliga.service;

import com.google.inject.Inject;
import pl.wroc.uni.ii.eliga.db.hibernate.HibernateDatabaseService;
import pl.wroc.uni.ii.eliga.db.model.Notice;
import pl.wroc.uni.ii.eliga.db.model.Student;
import pl.wroc.uni.ii.eliga.db.model.Teacher;
import pl.wroc.uni.ii.eliga.mail.GmailSender;
import pl.wroc.uni.ii.eliga.mail.Mail;
import pl.wroc.uni.ii.eliga.mail.MailSendException;

import java.util.Date;
import java.util.List;

public class NoticeAdderBean {
	
	@Inject
	private HibernateDatabaseService dbService;
	
	private GmailSender gmailSender;
	
	private Boolean sendEmail;
	private String noticeDescription;
	private String studentPesel;
	private Teacher teacher;
	
	public NoticeAdderBean() {
		sendEmail = true;
		gmailSender = new GmailSender();
	}

	public Boolean getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(Boolean sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getNoticeDescription() {
		return noticeDescription;
	}

	public void setNoticeDescription(String noticeDescription) {
		this.noticeDescription = noticeDescription;
	}
	
	public String getStudentPesel() {
		return studentPesel;
	}

	public void setStudentPesel(String studentPesel) {
		this.studentPesel = studentPesel;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void addNotice()  {
		Notice notice = crateNotice();
		dbService.save(notice);
		if (sendEmail) {
			Mail  mail =  createMail(); 
			try {
				gmailSender.send(mail);
			} catch (MailSendException e) {
				System.err.printf("Problem occure when try to send email: %s \n", mail.toString());
				e.printStackTrace();
			}
		}
	}

	private Notice crateNotice() {
		Student student = dbService.findStudentByPesel(studentPesel);
		return new Notice(noticeDescription, new Date(), student, teacher);
	}

	private Mail createMail() {
		String addres = dbService.findMailAdresByStudentPesel(studentPesel).get(0);
		return new Mail(noticeDescription, "Notice", addres);
	}

    public List<Teacher> getTeachers() {
        return dbService.fetchTeachers();
    }
	
}
