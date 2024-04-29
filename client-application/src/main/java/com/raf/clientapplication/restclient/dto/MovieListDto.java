package com.raf.clientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class MovieListDto {

	private List<TerminDtoOut> content = new ArrayList<>();

	public MovieListDto() {

	}

	public MovieListDto(List<TerminDtoOut> content) {
		this.content = content;
	}

	public List<TerminDtoOut> getContent() {
		return content;
	}

	public void setContent(List<TerminDtoOut> content) {
		this.content = content;
	}
}
