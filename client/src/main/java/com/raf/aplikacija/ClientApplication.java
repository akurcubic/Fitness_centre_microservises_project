package com.raf.aplikacija;


import com.raf.aplikacija.domain.Admin;
import com.raf.aplikacija.domain.Client;
import com.raf.aplikacija.domain.Manager;
import com.raf.aplikacija.view.LoginView;

import javax.swing.*;
import java.awt.*;

public class ClientApplication extends JFrame {

	private String token;
	private LoginView loginView;
	private Client client;
	private Manager manager;
	private Admin admin;

	//private MoviesView moviesView;

	private ClientApplication() throws IllegalAccessException, NoSuchMethodException {
		this.setTitle("Client Application");
		this.setSize(800, 500);
		this.setLayout(new BorderLayout());

		loginView = new LoginView();
		this.add(loginView, BorderLayout.NORTH);


		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static class InstanceHolder {
		private static ClientApplication instance;

		static {
			try {
				instance = new ClientApplication();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}


	public static ClientApplication getInstance() {
		return InstanceHolder.instance;
	}

}
