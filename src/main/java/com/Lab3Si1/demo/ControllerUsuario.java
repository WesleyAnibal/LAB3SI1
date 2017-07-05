package com.Lab3Si1.demo;

import static org.mockito.Matchers.contains;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gargoylesoftware.htmlunit.javascript.host.fetch.Request;

@RestController
public class ControllerUsuario {
	private List<Usuario> usuarios;
	
	
	private boolean cadastro(Usuario usuario) {
		if(this.usuarios == null){
			this.usuarios = new ArrayList<Usuario>();
		}
		if(!this.usuarios.contains(usuario))
			return this.usuarios.add(usuario);
		else {
			return false;
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, 
					consumes=MediaType.APPLICATION_JSON_VALUE, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> buscaUsuario(@RequestBody Usuario usuario) {
		if(usuarios.contains(usuario)){
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method=RequestMethod.POST, 
					value="/usuarios",
					consumes=MediaType.APPLICATION_JSON_VALUE)
	public void cadastrar(@RequestBody Usuario usuario) {
		cadastro(usuario);
	}
	
	
}
