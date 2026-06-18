package com.raf.zakazivanjeTreningaService.service.implementation;

import com.raf.zakazivanjeTreningaService.client.userservice.dto.BrojZakazanihTreningaDto;
import com.raf.zakazivanjeTreningaService.client.userservice.dto.IdUseraNapraviRezDto;
import com.raf.zakazivanjeTreningaService.client.userservice.dto.IdUseraOtkaziRezDto;
import com.raf.zakazivanjeTreningaService.client.userservice.dto.TipUseraDto;
import com.raf.zakazivanjeTreningaService.domain.Rezervacija;
import com.raf.zakazivanjeTreningaService.domain.Termin;
import com.raf.zakazivanjeTreningaService.dto.*;
import com.raf.zakazivanjeTreningaService.exception.NotFoundException;
import com.raf.zakazivanjeTreningaService.listener.helper.MessageHelper1;
import com.raf.zakazivanjeTreningaService.mapper.RezervacijaMapper;
import com.raf.zakazivanjeTreningaService.notifikacije.CancelClientNotification;
import com.raf.zakazivanjeTreningaService.notifikacije.CancelManagerNotification;
import com.raf.zakazivanjeTreningaService.notifikacije.SuccessfulReservationClientNotification;
import com.raf.zakazivanjeTreningaService.repository.RezervacijaRepository;
import com.raf.zakazivanjeTreningaService.repository.TerminRepository;
import com.raf.zakazivanjeTreningaService.service.RezervacijaService;
import io.github.resilience4j.retry.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Transactional
@Service
public class RezervacijaServiceImpl implements RezervacijaService {

    private RezervacijaRepository rezervacijaRepository;
    private RestTemplate userServiceRestTemplate;
    private TerminRepository terminRepository;
    private JmsTemplate jmsTemplate;
    private String brojZakazanihTreningaDestination;
    private String brojZakazanihTreningaDestinationSmanji;
    private String otkaziKlijentDestination;
    private String otkaziManagerDestination;
    private String dodajRezervacijuDestination;
    private MessageHelper1 messageHelper;
    private RezervacijaMapper rezervacijaMapper;
    private Retry rezervacijaServiceRetry;

    public RezervacijaServiceImpl(RezervacijaRepository rezervacijaRepository, RestTemplate userServiceRestTemplate, TerminRepository terminRepository, JmsTemplate jmsTemplate,@Value("${destination.create}") String brojZakazanihTreningaDestination,@Value("${destination.create2}") String brojZakazanihTreningaDestinationSmanji, MessageHelper1 messageHelper, RezervacijaMapper rezervacijaMapper, Retry rezervacijaServiceRetry,
    @Value("${destination.reservationclient}") String dodajRezervacijuDestination,@Value("${destination.cancelclient}") String otkaziKlijentDestination,@Value("${destination.cancelmanager}") String otkaziManagerDestination) {
        this.rezervacijaRepository = rezervacijaRepository;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.terminRepository = terminRepository;
        this.jmsTemplate = jmsTemplate;
        this.brojZakazanihTreningaDestination = brojZakazanihTreningaDestination;
        this.brojZakazanihTreningaDestinationSmanji = brojZakazanihTreningaDestinationSmanji;
        this.messageHelper = messageHelper;
        this.rezervacijaMapper = rezervacijaMapper;
        this.rezervacijaServiceRetry = rezervacijaServiceRetry;
        this.dodajRezervacijuDestination = dodajRezervacijuDestination;
        this.otkaziKlijentDestination = otkaziKlijentDestination;
        this.otkaziManagerDestination = otkaziManagerDestination;
    }

    @Override
    public void addReservation(RezervacijaDtoIn rezervacijaDto) {

        /*ResponseEntity<BrojZakazanihTreningaDto> brojZakazanihTreningaDtoResponseEntity = userServiceRestTemplate.exchange("/user/" +
                rezervacijaDto.getKlijentId() + "/brojZakazanihTreninga", HttpMethod.GET, null, BrojZakazanihTreningaDto.class);*/

        // Omotajte poziv dohvata broja zakazanih treninga s Retry pattern-om
        Supplier<ResponseEntity<BrojZakazanihTreningaDto>> supplier = Retry.decorateSupplier(rezervacijaServiceRetry, () ->
                userServiceRestTemplate.exchange("/user/" + rezervacijaDto.getKlijentId() + "/brojZakazanihTreninga", HttpMethod.GET, null, BrojZakazanihTreningaDto.class));

        // Izvršite omotanu funkciju za dohvaćanje broja zakazanih treninga
        ResponseEntity<BrojZakazanihTreningaDto> brojZakazanihTreningaDtoResponseEntity = supplier.get();


        Termin termin = terminRepository.findTerminByIdTermina(rezervacijaDto.getTerminId()).orElseThrow(() -> new NotFoundException(String.format("Termin with id: %s not found.", rezervacijaDto.getTerminId())));
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setIdKlijenta(rezervacijaDto.getKlijentId());
        rezervacija.setTermin(termin);


        int pogodnost = termin.getFiskulturnaSalaTrening().getFiskulturnaSala().getPogodnostZaVereneKlijente();
        if (brojZakazanihTreningaDtoResponseEntity.getBody().getBrojZakazanihTreninga() % pogodnost == 0 && brojZakazanihTreningaDtoResponseEntity.getBody().getBrojZakazanihTreninga() != 0) {

            rezervacija.setCena(0);
        } else {

            rezervacija.setCena(termin.getCenaTreninga());
        }
        rezervacijaRepository.save(rezervacija);
        jmsTemplate.convertAndSend(brojZakazanihTreningaDestination, messageHelper.createTextMessage(new IdUseraNapraviRezDto(rezervacijaDto.getKlijentId())));



        //saljem notifikaciju kada dodam rezervaciju

        ResponseEntity<ZajednickiDto> zajednickiDtoResponseEntity = userServiceRestTemplate.exchange("/user/" +
                rezervacijaDto.getKlijentId() + "/pronadjiKorisnika", HttpMethod.GET, null, ZajednickiDto.class);


        SuccessfulReservationClientNotification successfulReservationClientNotification = new SuccessfulReservationClientNotification();
        successfulReservationClientNotification.setEmail(zajednickiDtoResponseEntity.getBody().getEmail());
        successfulReservationClientNotification.setUsername(zajednickiDtoResponseEntity.getBody().getUsername());
        successfulReservationClientNotification.setGymName(zajednickiDtoResponseEntity.getBody().getNazivFiskulturneSale());
        successfulReservationClientNotification.setTypeOfWorkoutName(termin.getNazivTreninga());
        successfulReservationClientNotification.setPrice((long) rezervacija.getCena());
        successfulReservationClientNotification.setReceiverId(rezervacija.getIdKlijenta());

        successfulReservationClientNotification.setDate(termin.getDatum());

        successfulReservationClientNotification.setStart(termin.getVremeOd());

        successfulReservationClientNotification.setEnd(termin.getVremeDo());

        jmsTemplate.convertAndSend(dodajRezervacijuDestination,messageHelper.createTextMessage(successfulReservationClientNotification));


        termin.setKapacitet(termin.getKapacitet() - 1);
        if (termin.getKapacitet() <= 0) {

            termin.setDostupan(false);
        }
        terminRepository.save(termin);
    }

    @Override
    public void otkaziRezervaciju(OtkaziRezervacijuDto otkaziRezervacijuDto) {

        Long rezervacijaId = otkaziRezervacijuDto.getIdRezervacije();
        Long userId = otkaziRezervacijuDto.getIdUsera();

        System.out.println("id klijenta je " + userId);


        Rezervacija rezervacija = rezervacijaRepository.findById(rezervacijaId).orElseThrow(() -> new NotFoundException(String.format("Rezervacija with id: %s not found.", rezervacijaId)));
        Termin termin = terminRepository.findTerminByIdTermina(rezervacija.getTermin().getIdTermina()).orElseThrow(() -> new NotFoundException(String.format("Termin with id: %s not found.", rezervacija.getTermin().getIdTermina())));

        ResponseEntity<TipUseraDto> tipUseraDtoResponseEntity = userServiceRestTemplate.exchange("/user/" +
                userId + "/tipUsera", HttpMethod.GET, null, TipUseraDto.class);

        String tip = tipUseraDtoResponseEntity.getBody().getTipUsera();



        if (tip.equals("ROLE_CLIENT")) {

            ResponseEntity<ZajednickiDto> zajednickiDtoResponseEntity = userServiceRestTemplate.exchange("/user/" +
                    otkaziRezervacijuDto.getIdUsera() + "/pronadjiKorisnika", HttpMethod.GET, null, ZajednickiDto.class);

            jmsTemplate.convertAndSend(brojZakazanihTreningaDestinationSmanji, messageHelper.createTextMessage(new IdUseraOtkaziRezDto(rezervacija.getIdKlijenta())));

            CancelClientNotification cancelClientNotification = new CancelClientNotification();
            cancelClientNotification.setEmail(zajednickiDtoResponseEntity.getBody().getEmail());
            cancelClientNotification.setUsername(zajednickiDtoResponseEntity.getBody().getUsername());
            cancelClientNotification.setGymName(zajednickiDtoResponseEntity.getBody().getNazivFiskulturneSale());
            cancelClientNotification.setTypeOfWorkoutName(termin.getNazivTreninga());
            cancelClientNotification.setDate(termin.getDatum());
            cancelClientNotification.setStart(termin.getVremeOd());
            cancelClientNotification.setStart(termin.getVremeDo());
            cancelClientNotification.setReceiverId(otkaziRezervacijuDto.getIdUsera());

            jmsTemplate.convertAndSend(otkaziKlijentDestination,messageHelper.createTextMessage(cancelClientNotification));



            rezervacija.getTermin().setKapacitet(rezervacija.getTermin().getKapacitet() + 1);
            if (rezervacija.getTermin().getKapacitet() > 0) {

                rezervacija.getTermin().setDostupan(true);
            }
            terminRepository.save(rezervacija.getTermin());
            rezervacijaRepository.deleteById(rezervacijaId);
        } else if (tip.equals("ROLE_MANAGER")) {

            ResponseEntity<ZajednickiDto> zajednickiDtoResponseEntity1 = userServiceRestTemplate.exchange("/user/" +
                    otkaziRezervacijuDto.getIdUsera() + "/pronadjiKorisnika", HttpMethod.GET, null, ZajednickiDto.class);

            CancelManagerNotification cancelManagerNotification = new CancelManagerNotification();
            cancelManagerNotification.setEmail(zajednickiDtoResponseEntity1.getBody().getEmail());
            cancelManagerNotification.setGymName(zajednickiDtoResponseEntity1.getBody().getNazivFiskulturneSale());
            cancelManagerNotification.setTypeOfWorkoutName(termin.getNazivTreninga());
            cancelManagerNotification.setDate(termin.getDatum());
            cancelManagerNotification.setStart(termin.getVremeOd());
            cancelManagerNotification.setEnd(termin.getVremeDo());
            cancelManagerNotification.setReceiverId(otkaziRezervacijuDto.getIdUsera());

            jmsTemplate.convertAndSend(otkaziManagerDestination,messageHelper.createTextMessage(cancelManagerNotification));


            List<Rezervacija> rezervacijeZaTermine = new ArrayList<>();
            int brojac = 0;

            for (Rezervacija rezervacija1 : rezervacijaRepository.findAll()) {

                if (rezervacija1.getTermin().getIdTermina() == rezervacija.getTermin().getIdTermina()) {

                    rezervacijeZaTermine.add(rezervacija1);
                    brojac++;
                    jmsTemplate.convertAndSend(brojZakazanihTreningaDestinationSmanji, messageHelper.createTextMessage(new IdUseraOtkaziRezDto(rezervacija1.getIdKlijenta())));


                    //salji mejl klijentima
                    ResponseEntity<ZajednickiDto> zajednickiDtoResponseEntity = userServiceRestTemplate.exchange("/user/" +
                            rezervacija1.getIdKlijenta() + "/pronadjiKorisnika", HttpMethod.GET, null, ZajednickiDto.class);

                    System.out.println(zajednickiDtoResponseEntity.getBody().getUsername());

                    CancelClientNotification cancelClientNotification = new CancelClientNotification();
                    cancelClientNotification.setEmail(zajednickiDtoResponseEntity.getBody().getEmail());
                    cancelClientNotification.setUsername(zajednickiDtoResponseEntity.getBody().getUsername());
                    cancelClientNotification.setGymName(zajednickiDtoResponseEntity.getBody().getNazivFiskulturneSale());
                    cancelClientNotification.setTypeOfWorkoutName(termin.getNazivTreninga());
                    cancelClientNotification.setDate(termin.getDatum());
                    cancelClientNotification.setStart(termin.getVremeOd());
                    cancelClientNotification.setStart(termin.getVremeDo());
                    cancelClientNotification.setReceiverId(zajednickiDtoResponseEntity.getBody().getId());

                    jmsTemplate.convertAndSend(otkaziKlijentDestination,messageHelper.createTextMessage(cancelClientNotification));

                }
            }


            for (Rezervacija rezervacija1 : rezervacijeZaTermine) {

                rezervacijaRepository.delete(rezervacija1);
            }
            termin.setDostupan(false);
            termin.setKapacitet(termin.getKapacitet() + brojac);
            terminRepository.save(termin);
        }
    }

    @Override
    public RezervacijaListDto sveRezervacijeZaKlijenta(Long id) {

        List<RezervacijaDtoOut> list = new ArrayList<>();

        for(Rezervacija rezervacija : rezervacijaRepository.findAll()){

            if(rezervacija.getIdKlijenta() == id){

                list.add(rezervacijaMapper.rezervacijaToRezervacijaDtoOut(rezervacija));
            }
        }
        RezervacijaListDto rezervacijaListDto = new RezervacijaListDto();
        rezervacijaListDto.setContent(list);
        return rezervacijaListDto;
    }

    @Override
    public RezervacijaListDto sveRezervacijeZaSalu(String nazivSale) {

        List<RezervacijaDtoOut> list = new ArrayList<>();

        for(Rezervacija rezervacija : rezervacijaRepository.findAll()){

            if(rezervacija.getTermin().getNazivSale().equals(nazivSale)){

                list.add(rezervacijaMapper.rezervacijaToRezervacijaDtoOut(rezervacija));
            }
        }
        RezervacijaListDto rezervacijaListDto = new RezervacijaListDto();
        rezervacijaListDto.setContent(list);
        return rezervacijaListDto;
    }
}
