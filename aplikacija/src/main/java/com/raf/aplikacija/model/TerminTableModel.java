package com.raf.aplikacija.model;


import com.raf.aplikacija.restclient.dto.TerminDtoOut;
import com.raf.aplikacija.restclient.dto.TerminListDto;

import javax.swing.table.DefaultTableModel;

public class TerminTableModel extends DefaultTableModel {


	public TerminTableModel() throws IllegalAccessException, NoSuchMethodException {
		super(new String[]{"Id termina", "Sala","Trening","Tip","Cena","Datum","Dan","Vreme od","Vreme do","Kapacitet","Dostupan"}, 0);
	}

	private TerminListDto terminListDto = new TerminListDto();

	@Override
	public void addRow(Object[] row) {
		super.addRow(row);
		TerminDtoOut dto = new TerminDtoOut();
		dto.setIdTermina(Long.valueOf(String.valueOf(row[0])));
		dto.setNazivSale(String.valueOf(row[1]));
		dto.setNazivTreninga(String.valueOf(row[2]));
		dto.setTipTreninga(String.valueOf(row[3]));
		dto.setCenaTreninga(Integer.parseInt(String.valueOf(row[4])));
		dto.setDatum(String.valueOf(row[5]));
		dto.setDan(String.valueOf(row[6]));
		dto.setVremeOd(String.valueOf(row[7]));
		dto.setVremeDo(String.valueOf(row[8]));
		dto.setKapacitet(Integer.parseInt(String.valueOf(row[9])));
		dto.setDostupan(Boolean.parseBoolean(String.valueOf(row[10])));
		terminListDto.getContent().add(dto);
	}

	public TerminListDto getMovieListDto() {
		return terminListDto;
	}

	public void setMovieListDto(TerminListDto terminListDto) {
		this.terminListDto = terminListDto;
	}
}
