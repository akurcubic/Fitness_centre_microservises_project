package com.raf.zakazivanjeTreningaService.runner;

import com.raf.zakazivanjeTreningaService.domain.*;
import com.raf.zakazivanjeTreningaService.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class DataRunner implements CommandLineRunner {

    private TipTreningaRepository tipTreningaRepository;
    private TreningRepository treningRepository;
    private FiskulturnaSalaRepository fiskulturnaSalaRepository;
    private FiskulturnaSalaTreningRepository fiskulturnaSalaTreningRepository;
    private TerminRepository terminRepository;

    public DataRunner(TipTreningaRepository tipTreningaRepository, TreningRepository treningRepository, FiskulturnaSalaRepository fiskulturnaSalaRepository, FiskulturnaSalaTreningRepository fiskulturnaSalaTreningRepository, TerminRepository terminRepository) {
        this.tipTreningaRepository = tipTreningaRepository;
        this.treningRepository = treningRepository;
        this.fiskulturnaSalaRepository = fiskulturnaSalaRepository;
        this.fiskulturnaSalaTreningRepository = fiskulturnaSalaTreningRepository;
        this.terminRepository = terminRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        TipTreninga individulani = new TipTreninga("INDIVIDUALNI");
        TipTreninga grupni = new TipTreninga("GRUPNI");
        tipTreningaRepository.save(individulani);
        tipTreningaRepository.save(grupni);

        Trening powerlifting = new Trening("POWERLIFTING",individulani);
        Trening pilates = new Trening("PILATES",grupni);
        Trening kalistenika = new Trening("KALISTENIKA",individulani);
        Trening joga = new Trening("JOGA",grupni);
        treningRepository.save(powerlifting);
        treningRepository.save(pilates);
        treningRepository.save(kalistenika);
        treningRepository.save(joga);

        FiskulturnaSala ahilej = new FiskulturnaSala("Ahilej","Sirok izbor opreme",8,"08:00","23:00",10);
        FiskulturnaSala megaGym = new FiskulturnaSala("MegaGym","Veliki prostor",12,"09:00","21:00",7);
        fiskulturnaSalaRepository.save(ahilej);
        fiskulturnaSalaRepository.save(megaGym);

        FiskulturnaSalaTrening ahilejPowerlifting = new FiskulturnaSalaTrening(2000,60,1,ahilej,powerlifting);
        FiskulturnaSalaTrening ahilejJoga = new FiskulturnaSalaTrening(3000,90,7,ahilej,joga);

        FiskulturnaSalaTrening megaGymPilates = new FiskulturnaSalaTrening(1500,90,8,megaGym,pilates);
        FiskulturnaSalaTrening megaGymKalistenika = new FiskulturnaSalaTrening(1000,45,1,megaGym,kalistenika);

        fiskulturnaSalaTreningRepository.save(ahilejPowerlifting);
        fiskulturnaSalaTreningRepository.save(ahilejJoga);

        fiskulturnaSalaTreningRepository.save(megaGymPilates);
        fiskulturnaSalaTreningRepository.save(megaGymKalistenika);


        Termin termin = new Termin(ahilejPowerlifting);
        termin.setNazivSale(ahilejPowerlifting.getFiskulturnaSala().getNaziv());
        termin.setNazivTreninga(ahilejPowerlifting.getTrening().getNaziv());
        termin.setTipTreninga(ahilejPowerlifting.getTrening().getTipTreninga().getTip());
        termin.setCenaTreninga(ahilejPowerlifting.getCena());
        termin.setKapacitet(1);
        termin.setDatum("20/12/2023");
        termin.setDan("Sreda");
        termin.setVremeOd("14:00");
        termin.setVremeDo("15:00");
        terminRepository.save(termin);

        Termin termin2 = new Termin(ahilejJoga);
        termin2.setNazivSale(ahilejJoga.getFiskulturnaSala().getNaziv());
        termin2.setNazivTreninga(ahilejJoga.getTrening().getNaziv());
        termin2.setTipTreninga(ahilejJoga.getTrening().getTipTreninga().getTip());
        termin2.setCenaTreninga(ahilejJoga.getCena());
        termin2.setKapacitet(8);
        termin2.setDatum("25/12/2023");
        termin2.setDan("Ponedeljak");
        termin2.setVremeOd("18:00");
        termin2.setVremeDo("19:00");
        terminRepository.save(termin2);

        Termin termin3 = new Termin(megaGymKalistenika);
        termin3.setNazivSale(megaGymKalistenika.getFiskulturnaSala().getNaziv());
        termin3.setNazivTreninga(megaGymKalistenika.getTrening().getNaziv());
        termin3.setTipTreninga(megaGymKalistenika.getTrening().getTipTreninga().getTip());
        termin3.setCenaTreninga(megaGymKalistenika.getCena());
        termin3.setKapacitet(1);
        termin3.setDatum("20/12/2023");
        termin3.setDan("Sreda");
        termin3.setVremeOd("10:00");
        termin3.setVremeDo("12:00");
        terminRepository.save(termin3);

        Termin termin4 = new Termin(megaGymPilates);
        termin4.setNazivSale(megaGymPilates.getFiskulturnaSala().getNaziv());
        termin4.setNazivTreninga(megaGymPilates.getTrening().getNaziv());
        termin4.setTipTreninga(megaGymPilates.getTrening().getTipTreninga().getTip());
        termin4.setCenaTreninga(megaGymPilates.getCena());
        termin4.setKapacitet(10);
        termin4.setDatum("28/12/2023");
        termin4.setDan("Cetvrtak");
        termin4.setVremeOd("13:00");
        termin4.setVremeDo("14:00");
        terminRepository.save(termin4);
    }
}
