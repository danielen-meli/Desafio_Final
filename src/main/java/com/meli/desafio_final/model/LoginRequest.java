package com.meli.desafio_final.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {

	private String user;
	private String senha;
	
	public void setUser(String user) {
		this.user = user;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getUser() {
		return user;
	}
	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(user, senha);
	}
	
	
}
