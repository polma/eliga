package pl.wroc.uni.ii.eliga.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static pl.wroc.uni.ii.eliga.common.EligaSettings.MAIL_SEND_TIMEOUT_MILIS;
import static pl.wroc.uni.ii.eliga.common.EligaSettings.MAX_MAIL_SEND_TRIALS;

public class MailSendingService {
  private final Log log = LogFactory.getLog(MailSendingService.class);

  private final MailSender sender;
  public MailSendingService(MailSender sender) {
    this.sender = sender;
  }

  private final ExecutorService executor = Executors.newCachedThreadPool();

  public void send(Mail mail) {
    executor.submit(new MailSenderWithRetries(mail));
  }

  public void waitForSendCompletion() {
    try {
      executor.awaitTermination(MAIL_SEND_TIMEOUT_MILIS, MILLISECONDS);
    } catch (InterruptedException e) {
      log.error("Problem occurred during sending mails. It is likely, that some mails have not been sent", e);
    }
  }

  private class MailSenderWithRetries implements Runnable {
    private final Mail mail;

    private MailSenderWithRetries(Mail mail) {
      this.mail = mail;
    }

    @Override
    public void run() {
      MailSendException lastException = null;

      for(int i = 0; i < MAX_MAIL_SEND_TRIALS; ++i) {
        try {
          sender.send(mail);
          return;
        } catch (MailSendException e) {
          log.info("Trial to send mail failed", e);
          lastException = e;
        }
      }

      log.error("Could not sent mail", lastException);
    }
  }
}
