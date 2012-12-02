package pl.wroc.uni.ii.eliga.mail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static pl.wroc.uni.ii.eliga.common.EligaSettings.MAX_MAIL_SEND_TRIALS;

@RunWith(MockitoJUnitRunner.class)
public class MailSendingServiceTest {
  @Mock
  private MailSender sender;
  @Mock
  private Mail mail;

  private MailSendingService mailService;

  @Before
  public void setUp() {
    mailService = new MailSendingService(sender);
  }

  @Test
  public void sendsScheduledMail() throws MailSendException {
    mailService.send(mail);
    mailService.waitForSendCompletion();

    verify(sender).send(mail);
  }

  @Test
  public void retriesSendingMailIfFailed() throws MailSendException {
    doThrow(mailSendException()).when(sender).send(mail);
    mailService.send(mail);
    mailService.waitForSendCompletion();

    verify(sender, times(MAX_MAIL_SEND_TRIALS)).send(mail);
  }

  private MailSendException mailSendException() {
    return new MailSendException(new Exception());
  }
}
