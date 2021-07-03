package com.codeup.realtrail.services;

import com.codeup.realtrail.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {
    @Autowired
    private JavaMailSender verifiedEmail;

    @Value("")
    private String from;

    public void Send(Event event, String subject, String body){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(event.getOwner().getEmail());
        msg.setSubject(subject);
        msg.setText(body);

        try{
            this.verifiedEmail.send(msg);
        }
        catch(MailException ex){
            System.err.println(ex.getMessage());
        }
    }
}
