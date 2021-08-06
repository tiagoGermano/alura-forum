package br.com.alura.forum.controller.dto;

import br.com.alura.forum.modelo.Resposta;

public class RespostaDto {

	private final String mensagem;

	private final String autor;

	private final boolean solucao;

	public RespostaDto(Resposta resposta) {
		super();
		this.mensagem = resposta.getMensagem();
		this.autor = resposta.getAutor().getNome();
		this.solucao = resposta.getSolucao().booleanValue();
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getAutor() {
		return autor;
	}

	public boolean isSolucao() {
		return solucao;
	}

}
