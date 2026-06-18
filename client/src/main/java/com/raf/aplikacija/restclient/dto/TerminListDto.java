package com.raf.aplikacija.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class TerminListDto {

	private List<TerminDtoOut> content = new ArrayList<>();

	public TerminListDto() {

	}

	public TerminListDto(List<TerminDtoOut> content) {
		this.content = content;
	}

	public List<TerminDtoOut> getContent() {
		return content;
	}

	public void setContent(List<TerminDtoOut> content) {
		this.content = content;
	}
}
