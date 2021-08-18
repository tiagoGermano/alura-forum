package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class FormAuth {

	@NotNull
	private final String usuario;

	@NotNull
	private final String senha;

	public FormAuth(@NotNull String usuario, @NotNull String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(usuario, senha);
	}

}
