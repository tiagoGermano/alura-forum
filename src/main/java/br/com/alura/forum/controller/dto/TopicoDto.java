package br.com.alura.forum.controller.dto;

import org.springframework.data.domain.Page;

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
	
	public static Page<TopicoDto> converter(Page<Topico> topicos){
		return topicos.map(TopicoDto::new);
	}

}
