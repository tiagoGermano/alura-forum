package br.com.alura.forum.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

public class TopicoAlteraForm {

	@NotNull
	@NotEmpty
	@Size(min = 10, max = 40)
	private String titulo;

	@NotNull
	@NotEmpty
	@Size(max = 200)
	private String mensagem;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Optional<Topico> topOpt = topicoRepository.findById(id);
		
		if(topOpt.isPresent()) {
			Topico topico = topOpt.get();
			topico.setTitulo(this.titulo);
			topico.setMensagem(this.mensagem);
			
			return topico;
		}
		
		return null;		
		
	}

}
