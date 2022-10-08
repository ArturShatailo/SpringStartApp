package com.training.springstart.util.email;

import lombok.AllArgsConstructor;

import java.util.Properties;
import javax.mail.Session;

@AllArgsConstructor
public class EmailSender {

    String email;

    String subject;

    String body;

    public void send(){
        EmailUtil.sendEmail(email, subject, body);
    }

}
