package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		StringBuilder sb = new StringBuilder();
		sb.append("<h1>alura-forum REST API<h1/>");
		sb.append("<h3>Topicos</h3>");
		sb.append("<ul>");
			sb.append("<li><strong>GET</strong> /topicos - buscar todos tópicos</li>");
			sb.append("<li><strong>GET</strong> /topicos/{id} - buscar topico pelo id</li>");
			sb.append("<li><strong>POST</strong> /topicos - salvar topico</li>");
			sb.append("<li><strong>PUT</strong> /topicos/{id} - editar topico</li>");
			sb.append("<li><strong>DELETE</strong> /topicos/{id} - deletar topico</li>");
		sb.append("</ul>");
		return sb.toString();
	}
}
