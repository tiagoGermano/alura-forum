package br.com.alura.forum.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.modelo.Topico;

public class TopicoDto {

	private final Long id;
	
	private final String titulo;

	private final String mensagem;

	public TopicoDto(Topico topico) {
		super();
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
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
	
	public static List<TopicoDto> converter(List<Topico> topicos){
		return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
	}

}
