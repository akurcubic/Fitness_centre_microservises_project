package com.raf.aplikacija.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.domain.Admin;
import com.raf.aplikacija.domain.Client;
import com.raf.aplikacija.domain.Manager;
import com.raf.aplikacija.restclient.dto.*;
import okhttp3.*;

import javax.swing.*;
import java.io.IOException;

public class UserServiceRestClient {

	public static final String URL = "http://localhost:8080/api";

	public static final MediaType JSON
		= MediaType.get("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();

	public String login(String username, String password) throws IOException {

		TokenRequestDto tokenRequestDto = new TokenRequestDto(username, password);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));

		Request request = new Request.Builder()
			.url(URL + "/user/login")
			.post(body)
			.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();
			TokenResponseDto dto = objectMapper.readValue(json, TokenResponseDto.class);

			return dto.getToken();
		}

		throw new RuntimeException("Invalid username or password");
	}

	public void registerManager(String username,String password,String nazivSale,String name,String lastName,String email,String datumZaposljavanja,String datumRodjenja) throws IOException {

		ManagerCreateDto managerCreateDto = new ManagerCreateDto();
		managerCreateDto.setUsername(username);
		managerCreateDto.setPassword(password);
		managerCreateDto.setNazivFiskulturneSale(nazivSale);
		managerCreateDto.setFirstName(name);
		managerCreateDto.setLastName(lastName);
		managerCreateDto.setEmail(email);
		managerCreateDto.setDatumZaposljavanja(datumZaposljavanja);
		managerCreateDto.setDateOfBirth(datumRodjenja);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(managerCreateDto));

		Request request = new Request.Builder()
				.url(URL + "/user/registerManager")
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		if (response.code() == 201) {

			System.out.println("Uspesna registracija");
			JOptionPane.showMessageDialog(null, "Uspesna registracija", "Registracija", 1);

		} else {
			System.out.println(response.body());
			System.out.println("Nije uspesna registracija.");
		}
	}

		public void registerClient(String username,String password,String nazivSale,String name,String lastName,String email,String datumRodjenja) throws IOException {

			ClientCreateDto clientCreateDto = new ClientCreateDto();
			clientCreateDto.setUsername(username);
			clientCreateDto.setPassword(password);
			clientCreateDto.setNazivFiskulturneSale(nazivSale);
			clientCreateDto.setFirstName(name);
			clientCreateDto.setLastName(lastName);
			clientCreateDto.setEmail(email);
			clientCreateDto.setDateOfBirth(datumRodjenja);

			RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));

			Request request = new Request.Builder()
					.url(URL + "/user/registerClient")
					.post(body)
					.build();

			Call call = client.newCall(request);
			Response response = call.execute();

			if (response.code() == 201) {

				System.out.println("Uspesna registracija");
				JOptionPane.showMessageDialog(null,"Uspesna registracija","Registracija",1);

			}
			else{
				System.out.println(response.body());
				System.out.println("Nije uspesna registracija.");
			}
	}

	public void updateUser(String email,String firstName,String lastName,String oldUserName,String newUsername) throws IOException {

		UserUpdateDto userUpdateDto = new UserUpdateDto();
		userUpdateDto.setEmail(email);
		userUpdateDto.setFirstName(firstName);
		userUpdateDto.setLastName(lastName);
		userUpdateDto.setOldUserName(oldUserName);
		userUpdateDto.setUsername(newUsername);


		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(userUpdateDto));

		Request request = new Request.Builder()
				.url(URL + "/user/updateUser")
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		System.out.println(response.code());

		if(response.code() == 200){

			if(ClientApplication.getInstance().getClient() != null) {
				ClientApplication.getInstance().getClient().setEmail(email);
				ClientApplication.getInstance().getClient().setFirstName(firstName);
				ClientApplication.getInstance().getClient().setLastName(lastName);
				ClientApplication.getInstance().getClient().setUsername(newUsername);
			}
			else if(ClientApplication.getInstance().getManager() != null){

				ClientApplication.getInstance().getManager().setEmail(email);
				ClientApplication.getInstance().getManager().setFirstName(firstName);
				ClientApplication.getInstance().getManager().setLastName(lastName);
				ClientApplication.getInstance().getManager().setUsername(newUsername);
			}
			else if(ClientApplication.getInstance().getAdmin() != null){

				ClientApplication.getInstance().getAdmin().setEmail(email);
				ClientApplication.getInstance().getAdmin().setFirstName(firstName);
				ClientApplication.getInstance().getAdmin().setLastName(lastName);
				ClientApplication.getInstance().getAdmin().setUsername(newUsername);
			}
			JOptionPane.showMessageDialog(null, "Uspesna promena podataka", "Update", 1);


		}

	}

	public void postaviKorisnika(Integer id) throws IOException {

		Request request = new Request.Builder()
				.url(URL + "/user/" + id + "/pronadjiKorisnika")
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		if (response.code() == 200) {
			System.out.println("uspeo");
			String json = response.body().string();
			ZajednickiDto dto = objectMapper.readValue(json, ZajednickiDto.class);

			if(dto.getRole().equals("ROLE_CLIENT")){

				Client client = new Client();
				client.setId(dto.getId());
				client.setEmail(dto.getEmail());
				client.setFirstName(dto.getFirstName());
				client.setLastName(dto.getLastName());
				client.setUsername(dto.getUsername());
				client.setDateOfBirth(dto.getDateOfBirth());
				client.setAktivan(dto.isAktivan());
				client.setZabranjen(dto.isZabranjen());
				client.setRole(dto.getRole());
				//deo za klijenta
				client.setBrojClanskeKarte(dto.getBrojClanskeKarte());
				client.setBrojZakazanihTreninga(dto.getBrojZakazanihTreninga());
				client.setNazivFiskulturneSale(dto.getNazivFiskulturneSale());

				ClientApplication.getInstance().setClient(client);
			}
			else if(dto.getRole().equals("ROLE_MANAGER")){

				Manager manager = new Manager();
				manager.setId(dto.getId());
				manager.setEmail(dto.getEmail());
				manager.setFirstName(dto.getFirstName());
				manager.setLastName(dto.getLastName());
				manager.setUsername(dto.getUsername());
				manager.setDateOfBirth(dto.getDateOfBirth());
				manager.setAktivan(dto.isAktivan());
				manager.setZabranjen(dto.isZabranjen());
				manager.setRole(dto.getRole());
				//deo za menadzera
				manager.setNazivFiskulturneSale(dto.getNazivFiskulturneSale());
				manager.setDatumZaposljavanja(dto.getDatumZaposljavanja());

				ClientApplication.getInstance().setManager(manager);
			}
			else{

				Admin admin = new Admin();
				admin.setId(dto.getId());
				admin.setEmail(dto.getEmail());
				admin.setFirstName(dto.getFirstName());
				admin.setLastName(dto.getLastName());
				admin.setUsername(dto.getUsername());
				admin.setDateOfBirth(dto.getDateOfBirth());
				admin.setAktivan(dto.isAktivan());
				admin.setZabranjen(dto.isZabranjen());
				admin.setRole(dto.getRole());

				ClientApplication.getInstance().setAdmin(admin);
			}
		}
		else{
			System.out.println("Nije uspeo");
		}
	}

	public void promeniLozinku(String username,String oldPassword,String newPassword) throws IOException {

		PromenaLoznikeDtoIn promenaLoznikeDtoIn = new PromenaLoznikeDtoIn();
		promenaLoznikeDtoIn.setUsername(username);
		promenaLoznikeDtoIn.setOldPassword(oldPassword);
		promenaLoznikeDtoIn.setNewPassword(newPassword);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(promenaLoznikeDtoIn));

		Request request = new Request.Builder()
				.url(URL + "/user/promeniLozinku")
				.post(body)
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();
		System.out.println(response.code());

		if (response.code() == 200) {

			JOptionPane.showMessageDialog(null,"Uspesna promena lozinke","Promena lozinke",1);
		}
		else{
			JOptionPane.showMessageDialog(null,"Neuspesna promena lozinke","Promena lozinke",1);
		}
	}

	public UserListDto getSviKorisnici() throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/user/allUsers")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();

			return objectMapper.readValue(json, UserListDto.class);
		}

		throw new RuntimeException();
	}

	public void zabraniKoriscenjeAplikacije(String username) throws IOException {

		OmogociPristupUseruDtoIn omogociPristupUseruDtoIn = new OmogociPristupUseruDtoIn();
		omogociPristupUseruDtoIn.setUsername(username);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(omogociPristupUseruDtoIn));

		Request request = new Request.Builder()
				.url(URL + "/user/omoguciPristup")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.post(body)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		if (response.code() == 200) {

			System.out.println("Uspesna promena");
			JOptionPane.showMessageDialog(null, "Uspesna promena", "Obavestenje", 1);

		} else {
			System.out.println(response.body());
			System.out.println("Nije uspesna promane.");
		}


	}
}
