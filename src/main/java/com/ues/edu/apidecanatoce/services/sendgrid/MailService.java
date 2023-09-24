package com.ues.edu.apidecanatoce.services.sendgrid;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.ues.edu.apidecanatoce.controllers.sendgrid.BodyEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);
    public String sendTextEmail(BodyEmail email) throws IOException {
        Email from = new Email("misionesdecanato@gmail.com");
        String subject = email.getAsunto();
        Email to = new Email(email.getReceptor());
        Content content = new Content("text/plain", email.getMensaje());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            logger.info(response.getBody());
            return response.getBody();
        } catch (IOException ex) {
            throw ex;
        }
    }
}
