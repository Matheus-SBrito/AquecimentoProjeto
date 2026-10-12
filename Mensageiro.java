import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mensageiro {

    // Configurações para envio de e-mail (conta do projeto)
    private static final String REMETENTE = "mhvprojeto@gmail.com";
    private static final String SENHA_APP = "gsehckguewkscain"; // Senha de app de 16 caracteres sem espaços
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    public static void enviarMensagem(String destinatario, String assunto, String mensagem) {
        if (destinatario == null || destinatario.trim().isEmpty()) {
            System.err.println("Destinatário inválido.");
            return;
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        final String senhaSemEspacos = SENHA_APP.replaceAll("\\s+", "");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(REMETENTE, senhaSemEspacos);
            }
        });

        try {
            Message email = new MimeMessage(session);
            email.setFrom(new InternetAddress(REMETENTE));
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            email.setSubject(assunto != null ? assunto : "");
            email.setText(mensagem != null ? mensagem : "");

            Transport.send(email);
            System.out.println("E-mail enviado com sucesso para: " + destinatario);
        } catch (MessagingException e) {
            System.err.println("Não foi possível enviar e-mail para " + destinatario + " (" + e.getMessage() + ")");
        }
    }
}
