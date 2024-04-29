package com.raf.aplikacija.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.aplikacija.ClientApplication;
import com.raf.aplikacija.restclient.UserServiceRestClient;
import com.raf.aplikacija.token.TokenService;
import com.raf.aplikacija.token.TokenServiceImpl;
import io.jsonwebtoken.Claims;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginView extends JPanel {

	private JPanel inputPanel;
	private JPanel buttonPanel;

	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameInput;
	private JPasswordField passwordInput;

	private JButton loginButton;
	private JButton registerClientButton;
	private JButton registerManagerButton;

	private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
	private ObjectMapper objectMapper = new ObjectMapper();

	public LoginView() {
		super();
		this.setSize(200, 200);
		this.setLayout(new BorderLayout());

		initInputPanel();

		loginButton = new JButton("Login");

		loginButton.addActionListener((event) -> {
			try {
				String token = userServiceRestClient
						.login(usernameInput.getText(), String.valueOf(passwordInput.getPassword()));
				this.setVisible(false);
				ClientApplication.getInstance().setToken(token);
				System.out.println(token);

				TokenServiceImpl tokenService = new TokenServiceImpl();

				Claims claims = tokenService.parseToken(token);
				String role = claims.get("role", String.class);
				Integer id = claims.get("id", Integer.class);
				System.out.println(role);
				System.out.println(id);

				userServiceRestClient.postaviKorisnika(id);

				System.out.println(ClientApplication.getInstance().getClient());
				System.out.println(ClientApplication.getInstance().getManager());
				System.out.println(ClientApplication.getInstance().getAdmin());

				if(role.equals("ROLE_CLIENT")){

					if(ClientApplication.getInstance().getClient().isAktivan() && !ClientApplication.getInstance().getClient().isZabranjen()){

						ClientView clientView = new ClientView();
						clientView.setVisible(true);
					}
					else{

						JOptionPane.showMessageDialog(this,"Ne mozete da se ulogujete!","Obavestenje",1);
					}


				}
				else if(role.equals("ROLE_MANAGER")){

					if(ClientApplication.getInstance().getManager().isAktivan() && !ClientApplication.getInstance().getManager().isZabranjen()){

						ManagerView managerView = new ManagerView();
						managerView.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(this,"Ne mozete da se ulogujete!","Obavestenje",1);

					}

				}
				else if(role.equals("ROLE_ADMIN")){

					AdminView adminView = new AdminView();
					adminView.setVisible(true);
				}


			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (NoSuchMethodException e) {
				throw new RuntimeException(e);
			}
		});

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		registerClientButton = new JButton("Register client");
		registerClientButton.addActionListener(e -> {
			// Implementirajte funkcionalnost za registraciju klijenta
			// Na primer, otvaranje prozora za registraciju klijenta ili poziv odgovarajućih funkcija
			try {
				RegisterClientView registerClientView = new RegisterClientView();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		registerManagerButton = new JButton("Register Manager");
		registerManagerButton.addActionListener(e -> {
			// Implementirajte funkcionalnost za registraciju menadžera
			// Na primer, otvaranje prozora za registraciju menadžera ili poziv odgovarajućih funkcija
			try {
				RegisterManagerView registerManagerView = new RegisterManagerView();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		buttonPanel.add(registerClientButton);
		buttonPanel.add(registerManagerButton);

		this.add(inputPanel, BorderLayout.NORTH);
		this.add(loginButton,BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void initInputPanel() {
		inputPanel = new JPanel();

		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");

		usernameInput = new JTextField(20);
		passwordInput = new JPasswordField(20);

		inputPanel.add(usernameLabel);
		inputPanel.add(usernameInput);

		inputPanel.add(passwordLabel);
		inputPanel.add(passwordInput);
	}
}
