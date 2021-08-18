package br.com.alura.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.config.security.TokenService;
import br.com.alura.forum.controller.dto.TokenBearerDto;
import br.com.alura.forum.controller.form.FormAuth;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenBearerDto> autenticar(@RequestBody @Valid FormAuth formAuth){
		
		UsernamePasswordAuthenticationToken auth = formAuth.converter();
		try {
			Authentication authenticate = authManager.authenticate(auth);
			String token = tokenService.gerarToken(authenticate);
			return ResponseEntity.ok(new TokenBearerDto(token));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	
}
