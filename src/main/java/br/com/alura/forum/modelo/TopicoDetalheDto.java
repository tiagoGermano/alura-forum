package br.com.alura.forum.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.controller.dto.RespostaDto;

public class TopicoDetalheDto {

	private final Long id;
 
	private final String titulo;

	private final String mensagem;

	private final StatusTopico status;

	private final String autorTopico;

	private final String curso;

	private final List<RespostaDto> respostas;

	public TopicoDetalheDto(Topico topico) {
		super();
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.status = topico.getStatus();
		this.autorTopico = topico.getAutor().getNome();
		this.curso = topico.getCurso().getNome();
        this.respostas = new ArrayList<>();
		this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public String getAutorTopico() {
		return autorTopico;
	}

	public String getCurso() {
		return curso;
	}

	public List<RespostaDto> getRespostas() {
		return respostas;
	}

}
