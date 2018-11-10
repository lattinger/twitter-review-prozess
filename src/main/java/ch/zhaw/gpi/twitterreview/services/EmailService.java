package ch.zhaw.gpi.twitterreview.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;






@Service
public class EmailService {
     // Verdrahten von JavaMailSender
    @Autowired
    private JavaMailSender javaMailSender;
     // Sender-Adresse aus entsprechender Property-Eigenschaft lesen
    @Value("${mail.senderaddress}")
    private String senderAddress;
     /**
     * Sendet eine einfache Text-Mail
     *
     * @param to Empfänger
     * @param subject Betreff
     * @param body Mail-Text
     * @throws java.lang.Exception
     */
    public void sendSimpleMail(String to, String subject, String body) throws Exception {
        // Instanziert eine neue SimpleMail-Nachricht
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
         // Legt Empfänger, Betreff und Mail-Text fest
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom(senderAddress);
        simpleMailMessage.setReplyTo(senderAddress);
         // Versucht, die Mail abzusenden
        try {
            // Mail versenden
            javaMailSender.send(simpleMailMessage);
             // In der Konsole mitteilen, dass die Mail versandt wurde für einfacheres Debugging
            System.out.println("Mail erfolgreich versandt");
        } catch (MailException me) {
            // Fehlermeldung ausgeben in Konsole
            System.err.println(me.getLocalizedMessage());
             // Fehler weitergeben an aufrufende Methode
            throw new Exception("Mail senden fehlgeschlagen", me);
        }
    }
}