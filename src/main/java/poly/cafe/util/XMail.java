package poly.cafe.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class XMail {

    // Thông tin Gmail (App Password)
    private static final String USER = "tiendatng186@gmail.com";  
    private static final String PASS = "cdyb juoh jdpr rquq";   // App password Gmail
    //private static 
    /**
     * Gửi email có hỗ trợ file đính kèm
     * @param to      : Email người nhận
     * @param subject : Tiêu đề mail
     * @param content : Nội dung mail (hỗ trợ HTML)
     * @param file    : File đính kèm (có thể null)
     */
    public static void sendOPT(String to,String content)
            throws MessagingException, UnsupportedEncodingException {
        
        final String subject = "Mã OTP xác thực của bạn ";
        
        // Cấu hình SMTP (Gmail)
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo session có xác thực
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER, PASS);
            }
        });

        // Tạo message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USER, "Xưởng may mặc F3", "UTF-8")); // tên hiển thị UTF-8
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
        message.setSentDate(new Date());

        // Multipart (nội dung + file đính kèm)
        Multipart multipart = new MimeMultipart();

        // Nội dung chính (UTF-8, HTML)
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(content, "text/html; charset=UTF-8");
        multipart.addBodyPart(textPart);

        // Gắn multipart vào message
        message.setContent(multipart);

        // Gửi mail
        Transport.send(message);

        System.out.println("✅ Gửi mail thành công tới: " + to);
    }
    public static void sendMail(String to,String content, File file)
            throws MessagingException, UnsupportedEncodingException {
        
        final String subject = "Chúc mừng bạn đã ";
        
        // Cấu hình SMTP (Gmail)
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo session có xác thực
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER, PASS);
            }
        });

        // Tạo message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USER, "Xưởng may mặc F3", "UTF-8")); // tên hiển thị UTF-8
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
        message.setSentDate(new Date());

        // Multipart (nội dung + file đính kèm)
        Multipart multipart = new MimeMultipart();

        // Nội dung chính (UTF-8, HTML)
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(content, "text/html; charset=UTF-8");
        multipart.addBodyPart(textPart);

        // File đính kèm (nếu có)
        if (file != null && file.exists()) {
            MimeBodyPart attachPart = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(file);
            attachPart.setDataHandler(new DataHandler(fds));
            attachPart.setFileName(MimeUtility.encodeText(file.getName(), "UTF-8", null));
            multipart.addBodyPart(attachPart);
        }

        // Gắn multipart vào message
        message.setContent(multipart);

        // Gửi mail
        Transport.send(message);

        System.out.println("✅ Gửi mail thành công tới: " + to);
    }
}
