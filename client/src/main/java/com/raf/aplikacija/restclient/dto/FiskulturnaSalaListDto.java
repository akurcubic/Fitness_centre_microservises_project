package com.raf.aplikacija.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class FiskulturnaSalaListDto {

	private List<FiskulturnaSalaDtoOut> content = new ArrayList<>();

	public FiskulturnaSalaListDto() {

	}

	public FiskulturnaSalaListDto(List<FiskulturnaSalaDtoOut> content) {
		this.content = content;
	}

	public List<FiskulturnaSalaDtoOut> getContent() {
		return content;
	}

	public void setContent(List<FiskulturnaSalaDtoOut> content) {
		this.content = content;
	}
}
