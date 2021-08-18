package br.com.alura.forum.controller.dto;

public class TokenBearerDto {

	private final String token;

	private final String tipo;

	public TokenBearerDto(String token) {
		this.token = token;
		this.tipo = "Bearer";
	}

	public String getTipo() {
		return tipo;
	}

	public String getToken() {
		return token;
	}

}
