package com.Lab3Si1.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerUsuario {
	
	@Autowired
	UsuarioService clienteservice;
	
	@RequestMapping(method=RequestMethod.GET, 
					consumes=MediaType.APPLICATION_JSON_VALUE, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> buscaUsuario(@RequestBody Usuario usuario) {
			
		Usuario usu = clienteservice.buscarUsuario(usuario);
		if(usu != null){
			return new ResponseEntity<>(usu, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method=RequestMethod.POST, 
					value="/index",
					consumes=MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody Usuario usuario) {
		clienteservice.cadastro(usuario);
	}
	
	
}
