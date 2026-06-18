package com.raf.notifikacijeService.service;


import com.raf.notifikacijeService.domain.Mail;
import com.raf.notifikacijeService.domain.MailType;
import com.raf.notifikacijeService.repository.MailRepository;
import com.raf.notifikacijeService.repository.MailTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
public class EmailService {
    @Autowired
    public JavaMailSender mailSender;

    private MailRepository mailRepository;
    private MailTypeRepository mailTypeRepository;

    public void sendSimpleMessage(String to, String subject, String text, Long messageType, Long userId) {


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        //MailType mailType = mailTypeRepository.findById(messageType).orElse(null);
       // if(mailType==null){
           // return;
        //}
        Mail mail = new Mail();
        mail.setEmail(to);
        //mail.setMailType(mailType);
        mail.setContent(text);
        mail.setTimeofsending(java.time.LocalDate.now());
        mail.setReceiverId(userId);


        mailSender.send(message);
        //mailRepository.save(mail);
    }

}
