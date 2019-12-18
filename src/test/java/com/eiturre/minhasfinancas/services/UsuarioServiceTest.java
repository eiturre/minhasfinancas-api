package com.eiturre.minhasfinancas.services;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.eiturre.minhasfinancas.model.repository.UsuarioRepository;
import com.eiturre.minhasfinancas.service.UsuarioService;
import com.eiturre.minhasfinancas.service.impl.UsuarioServiceImpl;

@ExtendWith( SpringExtension.class )
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@Autowired
	UsuarioService service;
	
	@MockBean
	UsuarioRepository repository;
	
	@Before(value = "")
	public void setUp() {
		service = new UsuarioServiceImpl(repository);
	}
	
	@Test
	public void deveValidarEmail() {
		// cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		//acao
		service.validarEmail("email@email.com");
	}
	
	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		//cenario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
//		Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build();
		
		//acao
		service.validarEmail("email@email.com");
	}
}
