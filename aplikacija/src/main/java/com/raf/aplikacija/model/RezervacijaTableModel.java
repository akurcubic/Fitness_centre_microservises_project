package com.raf.aplikacija.model;

import com.raf.aplikacija.restclient.dto.RezervacijaDtoOut;
import com.raf.aplikacija.restclient.dto.RezervacijaListDto;

import javax.swing.table.DefaultTableModel;

public class RezervacijaTableModel extends DefaultTableModel {

    public RezervacijaTableModel() throws IllegalAccessException, NoSuchMethodException {
        super(new String[]{"Id rezervacije","Id termina", "Sala","Trening","Tip","Cena","Datum","Dan","Vreme od","Vreme do"}, 0);
    }

    private RezervacijaListDto rezervacijaListDto = new RezervacijaListDto();

    @Override
    public void addRow(Object[] row) {
        super.addRow(row);
        RezervacijaDtoOut dto = new RezervacijaDtoOut();
        dto.setIdRezervacije(Long.valueOf(String.valueOf(row[0])));
        dto.setIdTermina(Long.valueOf(String.valueOf(row[1])));
        dto.setNazivSale(String.valueOf(row[2]));
        dto.setNazivTreninga(String.valueOf(row[3]));
        dto.setTipTreninga(String.valueOf(row[4]));
        dto.setCenaTreninga(Integer.parseInt(String.valueOf(row[5])));
        dto.setDatum(String.valueOf(row[6]));
        dto.setDan(String.valueOf(row[7]));
        dto.setVremeOd(String.valueOf(row[8]));
        dto.setVremeDo(String.valueOf(row[9]));
        rezervacijaListDto.getContent().add(dto);
    }

    public RezervacijaListDto getRezervacijaListDto() {
        return rezervacijaListDto;
    }

    public void setRezervacijaListDto(RezervacijaListDto rezervacijaListDto) {
        this.rezervacijaListDto = rezervacijaListDto;
    }
}
