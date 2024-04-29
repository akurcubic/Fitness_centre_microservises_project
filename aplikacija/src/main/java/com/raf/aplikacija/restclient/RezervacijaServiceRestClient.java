package com.raf.aplikacija.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.restclient.dto.*;
import okhttp3.*;

import javax.swing.*;
import java.io.IOException;

public class RezervacijaServiceRestClient {

	public static final String URL = "http://localhost:8081/api";

	public static final MediaType JSON
		= MediaType.get("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();

	public FiskulturnaSalaListDto getSala() throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
			.url(URL + "/fiskulturnaSala/all")
			.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
			.get()
			.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();

			return objectMapper.readValue(json, FiskulturnaSalaListDto.class);
		}

		throw new RuntimeException();
	}

	public TerminListDto getTerminiZaOdredjenuSalu(String nazivFiskulturneSale) throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/termin/" + nazivFiskulturneSale + "/terminZaOdredjenuSalu")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();


		if (response.code() == 200) {
			String json = response.body().string();
			System.out.println("vracam sve termine");
			return objectMapper.readValue(json, TerminListDto.class);
		}
		else {
			System.out.println("nisam vratio termine");
			return new TerminListDto();
		}
	}


	public void zakaziTermin(Long idTermina, Long idKorisnika) throws IOException {

		RezervacijaDtoIn rezervacijaDtoIn = new RezervacijaDtoIn();
		rezervacijaDtoIn.setKlijentId(idKorisnika);
		rezervacijaDtoIn.setTerminId(idTermina);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(rezervacijaDtoIn));

		Request request = new Request.Builder()
				.url(URL + "/rezervacija/addRezervacija")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		System.out.println(response.code());

		if(response.code() == 201){

			JOptionPane.showMessageDialog(null, "Uspesna rezervacija", "Rezervacija", 1);
		}
		else{
			JOptionPane.showMessageDialog(null, "Nije uspesna rezervacija", "Rezervacija", 1);

		}

	}

	public void otkaziRezervaciju(Long idRezervacije,Long userId) throws IOException {

		OtkaziRezervacijuDto otkaziRezervacijuDto = new OtkaziRezervacijuDto();
		otkaziRezervacijuDto.setIdRezervacije(idRezervacije);
		otkaziRezervacijuDto.setIdUsera(userId);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(otkaziRezervacijuDto));

		Request request = new Request.Builder()
				.url(URL + "/rezervacija/otkaziRezervaciju")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		JOptionPane.showMessageDialog(null, "Rezervacija je otkazana", "Rezervacija", 1);

	}

	public RezervacijaListDto getRezervacijeZaKlijenta(Long id) throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/rezervacija/" + id + "/rezervacijeZaKlijenta")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		System.out.println(response.body().toString());

		if (response.code() == 200) {
			String json = response.body().string();

			return objectMapper.readValue(json, RezervacijaListDto.class);
		}
		else
			return new RezervacijaListDto();
	}

	public RezervacijaListDto getRezervacijeZaSalu(String nazivSale) throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/rezervacija/" + nazivSale + "/rezervacijeZaSalu")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		System.out.println(response.body().toString());

		if (response.code() == 200) {
			String json = response.body().string();

			return objectMapper.readValue(json, RezervacijaListDto.class);
		}
		else
			return new RezervacijaListDto();
	}

	public FiskulturnaSalaDtoOut pronadjiSalu(String nazivSale) throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/fiskulturnaSala/" + nazivSale + "/pronadjiSaluPoNazivu")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		System.out.println(response.body().toString());

		if (response.code() == 200) {
			String json = response.body().string();
			return objectMapper.readValue(json, FiskulturnaSalaDtoOut.class);
		}
		else
			return null;

	}

	public void updateSala(int brojTrenera,String pocetakRV,String krajRV,String opis,int pogodnost) throws IOException {

		FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto = new FiskulturnaSalaUpdateDto();
		fiskulturnaSalaUpdateDto.setBrojTrenera(brojTrenera);
		fiskulturnaSalaUpdateDto.setPocetakRadnogVremena(pocetakRV);
		fiskulturnaSalaUpdateDto.setKrajRadnogVremena(krajRV);
		fiskulturnaSalaUpdateDto.setOpis(opis);
		fiskulturnaSalaUpdateDto.setPogodnostZaVereneKlijente(pogodnost);
		fiskulturnaSalaUpdateDto.setStariNaziv(ClientApplication.getInstance().getManager().getNazivFiskulturneSale());
		fiskulturnaSalaUpdateDto.setNaziv(ClientApplication.getInstance().getManager().getNazivFiskulturneSale());

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(fiskulturnaSalaUpdateDto));

		Request request = new Request.Builder()
				.url(URL + "/fiskulturnaSala/updateFiskulturnaSala")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		if(response.code() == 200){

			JOptionPane.showMessageDialog(null, "Uspesna promena podataka", "Promena podataka", 1);

		}
		else
			JOptionPane.showMessageDialog(null, "Neuspesna promena podataka", "Promena podataka", 1);
	}

	public void dodajTipTreninga(String tip) throws IOException {

		TipTreningaDtoIn tipTreningaDtoIn = new TipTreningaDtoIn();
		tipTreningaDtoIn.setTip(tip);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tipTreningaDtoIn));

		Request request = new Request.Builder()
				.url(URL + "/tipTreninga/addTipTreninga")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		if (response.code() == 201) {
			JOptionPane.showMessageDialog(null, "Uspesna kreiran tip treninga!", "Obavestenje", 1);

		} else {
			JOptionPane.showMessageDialog(null, "Nije uspesno kreitan tip treninga", "Obavestenje", 1);

		}
	}

	public TipTreningaListDto getTipTreningaAll() throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/tipTreninga/all")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();

			return objectMapper.readValue(json, TipTreningaListDto.class);
		}

		throw new RuntimeException();

	}

	public void dodajTrening(String nazivTreninga,String tip) throws IOException {

		TreningaDtoIn treningaDtoIn = new TreningaDtoIn();
		treningaDtoIn.setNaziv(nazivTreninga);
		treningaDtoIn.setTip(tip);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(treningaDtoIn));

		Request request = new Request.Builder()
				.url(URL + "/trening/addTrening")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		if(response.code() == 201){

			JOptionPane.showMessageDialog(null, "Uspesno dodat trening", "Promena podataka", 1);

		}
		else
			JOptionPane.showMessageDialog(null, "Neuspesna dodat trening", "Promena podataka", 1);
	}

	public TreningListDto getSviTreninzi() throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/trening/all")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {

			String json = response.body().string();
			return objectMapper.readValue(json, TreningListDto.class);
		}


		throw new RuntimeException();
	}

	public void dodajTreningZaSalu(String nazivSale,String trening,int cena,int trajanje,int kapacitet) throws IOException {


		FiskulturnaSalaTreningDtoIn fiskulturnaSalaTreningDtoIn = new FiskulturnaSalaTreningDtoIn();
		fiskulturnaSalaTreningDtoIn.setNazivSale(nazivSale);
		fiskulturnaSalaTreningDtoIn.setTrening(trening);
		fiskulturnaSalaTreningDtoIn.setCena(cena);
		fiskulturnaSalaTreningDtoIn.setTrajanjeTreninga(trajanje);
		fiskulturnaSalaTreningDtoIn.setKapacitet(kapacitet);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(fiskulturnaSalaTreningDtoIn));

		Request request = new Request.Builder()
				.url(URL + "/fiskulturnaSalaTrening/addFiskulturnaSalaAndTrening")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();


		JOptionPane.showMessageDialog(null, "Uspesno dodat trening za salu", "Obavestenje", 1);
	}

	public FiskulturnaSalaTreningListDto sviTreninziZaSalu(String nazivSale) throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()

				.url(URL + "/fiskulturnaSalaTrening/" + nazivSale + "/treninziZaSalu")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		System.out.println(response.body().toString());

		if (response.code() == 200) {
			String json = response.body().string();

			return objectMapper.readValue(json, FiskulturnaSalaTreningListDto.class);
		}
		else
			return new FiskulturnaSalaTreningListDto();
	}

	public void dodajTermin(String nazivSale,String trening,String dan,String datum,String vremeOd,String vremeDo) throws IOException {

		TerminDtoIn terminDtoIn = new TerminDtoIn();
		terminDtoIn.setNazivSale(nazivSale);
		terminDtoIn.setNazivTreninga(trening);
		terminDtoIn.setDan(dan);
		terminDtoIn.setDatum(datum);
		terminDtoIn.setVremeOd(vremeOd);
		terminDtoIn.setVremeDo(vremeDo);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(terminDtoIn));

		Request request = new Request.Builder()
				.url(URL + "/termin/addTermin")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();
		System.out.println(response.code());

		if (response.code() == 201) {
			JOptionPane.showMessageDialog(null, "Uspesna dodat termin!", "Obavestenje", 1);

		} else {
			JOptionPane.showMessageDialog(null, "Nije dodat termin", "Obavestenje", 1);

		}
	}

	public void dodajSalu(int brojTrenera,String pocetakRV,String krajRV,String opis,int pogodnost,String nazivSale) throws IOException {


		FiskulturnaSalaDtoIn fiskulturnaSalaDtoIn = new FiskulturnaSalaDtoIn();
		fiskulturnaSalaDtoIn.setBrojTrenera(brojTrenera);
		fiskulturnaSalaDtoIn.setPocetakRadnogVremena(pocetakRV);
		fiskulturnaSalaDtoIn.setKrajRadnogVremena(krajRV);
		fiskulturnaSalaDtoIn.setOpis(opis);
		fiskulturnaSalaDtoIn.setPogodnostZaVereneKlijente(pogodnost);
		fiskulturnaSalaDtoIn.setNaziv(nazivSale);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(fiskulturnaSalaDtoIn));

		Request request = new Request.Builder()
				.url(URL + "/fiskulturnaSala/addFiskulturnaSala")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();
		System.out.println(response.code());

		if(response.code() == 201){

			JOptionPane.showMessageDialog(null, "Sala je dodata", "Obavestenje", 1);

		}
		else
			JOptionPane.showMessageDialog(null, "Sala nije dodata", "Obavestenje", 1);
	}

}
