package com.ues.edu.apidecanatoce.services.sendgrid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;

import com.sendgrid.helpers.mail.objects.Email;

import com.sendgrid.helpers.mail.objects.Personalization;
import com.ues.edu.apidecanatoce.controllers.sendgrid.BodyEmail;
import com.ues.edu.apidecanatoce.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);
    /*
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
     */

    @Value("${SENDGRID_API_KEY}")
    private String secretKey;

    @Value("${TEMPLATE_ID}")
    private String plantillaKey;

    public String send(BodyEmail email) throws IOException {
        // the sender email should be the same as we used to Create a Single Sender Verification
        Email from = new Email("decanatomisiones@gmail.com");
        Email to = new Email(email.getEmail());
        Mail mail = new Mail();
        // we create an object of our static class feel free to change the class on it's own file
        // I try to keep every think simple
        DynamicTemplatePersonalization personalization = new DynamicTemplatePersonalization();
        personalization.addTo(to);

        mail.setFrom(from);
        mail.setSubject(email.getAsunto());
        // This is the first_name variable that we created on the template
        personalization.addDynamicTemplateData("title", email.getTitulo());
        personalization.addDynamicTemplateData("receptor", email.getReceptor());
        personalization.addDynamicTemplateData("content_body", email.getMensaje());
        personalization.addDynamicTemplateData("content_center", email.getCentro());
        personalization.addDynamicTemplateData("code", email.getCodigo());
        personalization.addDynamicTemplateData("content_down", email.getAbajo());
        mail.addPersonalization(personalization);
        mail.setTemplateId("d-87c54ebd6bb8406ba16b44afc606bca6");
        // this is the api key
        SendGrid sg = new SendGrid("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");
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());
            return response.getBody();
        } catch (IOException ex) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "No se envió el correo, verifique sus datos o la red");
        }
    }

    // This class handels the dynamic data for the template
    // Feel free to customise this class our to putted on other file
    private static class DynamicTemplatePersonalization extends Personalization {

        @JsonProperty(value = "dynamic_template_data")
        private Map<String, Object> dynamic_template_data;

        @JsonProperty("dynamic_template_data")
        public Map<String, Object> getDynamicTemplateData() {
            if (dynamic_template_data == null) {
                return Collections.<String, Object>emptyMap();
            }
            return dynamic_template_data;
        }

        public void addDynamicTemplateData(String key, String value) {
            if (dynamic_template_data == null) {
                dynamic_template_data = new HashMap<String, Object>();
                dynamic_template_data.put(key, value);
            } else {
                dynamic_template_data.put(key, value);
            }
        }

    }
}
