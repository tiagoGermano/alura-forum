package br.com.alura.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicoAlteraForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.modelo.TopicoDetalheDto;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("topico")
public class TopicoController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public Page<TopicoDto> listar(@RequestParam(required = false) String nomeCurso, 
			@PageableDefault(page = 0, size = 10, sort = "titulo", direction = Direction.ASC) Pageable pageable){
		
		if(nomeCurso != null) {
			Page<Topico> topicos = topicoRepository.findByCursoNomeIgnoreCase(nomeCurso, pageable);
			return TopicoDto.converter(topicos);			
			
		} else {
			Page<Topico> topicoPage = topicoRepository.findAll(pageable);
			return TopicoDto.converter(topicoPage);			
		}
	}
	
	@PostMapping
	public ResponseEntity<TopicoDto> salvar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI location = uriBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(location).body(new TopicoDto(topico));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoDetalheDto> buscar(@PathVariable Long id) {
		Optional<Topico> topicoOpt = topicoRepository.findById(id);
		
		if(topicoOpt.isPresent()) {
			Topico topico = topicoOpt.get();
			return ResponseEntity.ok(new TopicoDetalheDto(topico));
		} 
			
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> alterar(@PathVariable Long id, @RequestBody @Valid TopicoAlteraForm form) {
		Optional<Topico> topicoOpt = topicoRepository.findById(id);
		
		if(topicoOpt.isPresent()) {
			Topico topico = form.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topico)); 
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Optional<Topico> topicoOpt = topicoRepository.findById(id);
		
		if(topicoOpt.isPresent()) {
			topicoRepository.delete(topicoOpt.get());
			return ResponseEntity.ok().build(); 
		}
		
		return ResponseEntity.notFound().build();
	}
}
