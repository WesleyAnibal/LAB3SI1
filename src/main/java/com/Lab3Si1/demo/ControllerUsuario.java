package com.Lab3Si1.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class ControllerUsuario {
	
	@Autowired
	UsuarioService clienteservice;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> buscaUsuario(@PathVariable("id") Long id) {
			
		Usuario usu = clienteservice.buscarUsuario(id);
		if(usu != null){
			return new ResponseEntity<>(usu, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method=RequestMethod.POST,
					consumes=MediaType.APPLICATION_JSON_VALUE,
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		System.out.println(usuario.getId());
		return new ResponseEntity<>(clienteservice.cadastro(usuario), HttpStatus.OK);
	}
	
	
}
