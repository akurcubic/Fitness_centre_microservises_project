package com.raf.clientapplication.model;

import javax.swing.table.DefaultTableModel;

import com.raf.clientapplication.restclient.dto.MovieDto;
import com.raf.clientapplication.restclient.dto.MovieListDto;
import com.raf.clientapplication.restclient.dto.TerminDtoOut;

public class MovieTableModel extends DefaultTableModel {


	public MovieTableModel() throws IllegalAccessException, NoSuchMethodException {
		super(new String[]{"Naziv sale", "Naziv treninga"}, 0);
	}

	private MovieListDto movieListDto = new MovieListDto();

	@Override
	public void addRow(Object[] row) {
		super.addRow(row);
		TerminDtoOut dto = new TerminDtoOut();
		dto.setNazivSale(String.valueOf(row[0]));
		dto.setNazivTreninga(String.valueOf(row[1]));
		dto.setIdTermina(Long.valueOf(String.valueOf(row[2])));
		movieListDto.getContent().add(dto);
	}

	public MovieListDto getMovieListDto() {
		return movieListDto;
	}

	public void setMovieListDto(MovieListDto movieListDto) {
		this.movieListDto = movieListDto;
	}
}
