package com.eiturre.minhasfinancas.api.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eiturre.minhasfinancas.api.dto.UsuarioDTO;
import com.eiturre.minhasfinancas.exception.ErroAutenticacao;
import com.eiturre.minhasfinancas.exception.RegraNegocioException;
import com.eiturre.minhasfinancas.model.entity.Usuario;
import com.eiturre.minhasfinancas.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {
	
	private UsuarioService service;
	
	public UsuarioResource(UsuarioService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity save( @RequestBody UsuarioDTO user) {
		Usuario usuario  = Usuario.builder()
				.nome(user.getNome())
				.email(user.getEmail())
				.senha(user.getSenha()).build();
		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar( @RequestBody UsuarioDTO user) {
		try {
			Usuario usuarioAutenticado = service.autenticar(user.getEmail(), user.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
}
