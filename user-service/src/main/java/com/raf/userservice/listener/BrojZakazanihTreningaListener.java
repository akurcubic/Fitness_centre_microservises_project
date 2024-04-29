package com.raf.userservice.listener;

import com.raf.userservice.dto.IdUseraNapraviRezDto;
import com.raf.userservice.dto.IdUseraOtkaziRezDto;
import com.raf.userservice.service.UserService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class BrojZakazanihTreningaListener {

    private MessageHelper messageHelper;
    private UserService userService;

    public BrojZakazanihTreningaListener(MessageHelper messageHelper, UserService userService) {
        this.messageHelper = messageHelper;
        this.userService = userService;
    }

    @JmsListener(destination = "${destination.create}", concurrency = "5-10")
    public void incrementReservationCount(Message message) throws JMSException {
        System.out.println("povecao");
        IdUseraNapraviRezDto povecajBrojZakazanihTreningaDto = messageHelper.getMessage(message, IdUseraNapraviRezDto.class);
        System.out.println(povecajBrojZakazanihTreningaDto);
        userService.povecajBrojZakazanihTreninga(povecajBrojZakazanihTreningaDto);
    }

    @JmsListener(destination = "${destination.create2}", concurrency = "5-10")
    public void smanjiReservationCount(Message message) throws JMSException {
        IdUseraOtkaziRezDto smanjiBrojZakazanihTreningaDto = messageHelper.getMessage(message, IdUseraOtkaziRezDto.class);
        System.out.println(smanjiBrojZakazanihTreningaDto);
        userService.smanjiBrojZakazanihTreninga(smanjiBrojZakazanihTreningaDto);
    }

}
