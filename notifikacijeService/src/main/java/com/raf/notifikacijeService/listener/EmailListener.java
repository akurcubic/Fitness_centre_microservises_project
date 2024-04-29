package com.raf.notifikacijeService.listener;

import com.raf.notifikacijeService.domain.*;
import com.raf.notifikacijeService.listener.helper.MessageHelper;
import com.raf.notifikacijeService.service.EmailService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class EmailListener {

    private MessageHelper messageHelper;
    private EmailService emailService;

    public EmailListener(MessageHelper messageHelper, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }
    @JmsListener(destination = "${destination.register}", concurrency = "5-10")
    public void register(Message message) throws JMSException {
        RegisterNotification registerNotification = messageHelper.getMessage(message, RegisterNotification.class);
        emailService.sendSimpleMessage(registerNotification.getEmail(), "Welcome to Gym!", registerNotification.toString(), 1L, registerNotification.getReceiverId());
    }

    @JmsListener(destination = "${destination.changepassword}", concurrency = "5-10")
    public void changePassword(Message message) throws JMSException {
        ChangePasswordNotification registerNotification = messageHelper.getMessage(message, ChangePasswordNotification.class);
        emailService.sendSimpleMessage(registerNotification.getEmail(), "Confirm password change", registerNotification.toString(), 2L, registerNotification.getReceiverId());
    }

    @JmsListener(destination = "reminder", concurrency = "5-10")
    public void reservationReminder(Message message) throws JMSException{
        ReminderNotification reminderNotification = messageHelper.getMessage(message, ReminderNotification.class);
        emailService.sendSimpleMessage(reminderNotification.getEmail(), "Reservation reminder", reminderNotification.toString(), 3L, reminderNotification.getReceiverId());
    }

    @JmsListener(destination = "${destination.reservationclient}", concurrency = "5-10")
    public void successfulReservationClient(Message message) throws JMSException{
        System.out.println("stigla poruka");
        SuccessfulReservationClientNotification successfulReservationClientNotification = messageHelper.getMessage(message, SuccessfulReservationClientNotification.class);
        emailService.sendSimpleMessage(successfulReservationClientNotification.getEmail(), "Successful reservation", successfulReservationClientNotification.toString(), 4L, successfulReservationClientNotification.getReceiverId());
    }

    @JmsListener(destination = "reservationmanager", concurrency = "5-10")
    public void successfulReservationManager(Message message) throws JMSException{
        SuccessfulReservationManagerNotification successfulReservationManagerNotification = messageHelper.getMessage(message, SuccessfulReservationManagerNotification.class);
        emailService.sendSimpleMessage(successfulReservationManagerNotification.getEmail(), "Successful reservation", successfulReservationManagerNotification.toString(), 5L, successfulReservationManagerNotification.getReceiverId());
    }

    @JmsListener(destination = "${destination.cancelclient}", concurrency = "5-10")
    public void cancelReservationClient(Message message) throws JMSException{
        System.out.println("otkazao klijentu");
        CancelClientNotification cancelClientNotification = messageHelper.getMessage(message, CancelClientNotification.class);
        emailService.sendSimpleMessage(cancelClientNotification.getEmail(), "Reservation cancelled", cancelClientNotification.toString(), 6L, cancelClientNotification.getReceiverId());

    }

    @JmsListener(destination = "${destination.cancelmanager}", concurrency = "5-10")
    public void cancelReservationManager(Message message) throws JMSException{
        CancelManagerNotification cancelManagerNotification = messageHelper.getMessage(message, CancelManagerNotification.class);
        emailService.sendSimpleMessage(cancelManagerNotification.getEmail(), "Reservation cancelled", cancelManagerNotification.toString(), 7L, cancelManagerNotification.getReceiverId());
    }


}
