package br.com.Lab3Si1.services;

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

	@Autowired
	SerieService serieService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> buscaUsuario(@PathVariable("id") Long id) {

		Usuario usu = clienteservice.buscarUsuario(id);
		if (usu != null) {
			System.out.println(usu.getSeries().toString());
			return new ResponseEntity<>(usu, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Usuario> deletarCliente(@PathVariable Long id) {

		if (clienteservice.remover(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		System.out.println(usuario.getId());
		return new ResponseEntity<>(clienteservice.cadastro(usuario), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="{id}/series/{i}")
	public ResponseEntity<Serie> deletarSerie(@RequestBody Serie serie, @PathVariable Long id, @PathVariable Long i){
		Usuario usu = clienteservice.buscarUsuario(id);
		if(usu.removerSerie(i)) {
			clienteservice.cadastro(usu);
			return new ResponseEntity<Serie>(serie, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serie> adicionarSerie(@RequestBody Serie serie, @PathVariable("id") Long id) {
		Usuario usu = clienteservice.buscarUsuario(id);
		serieService.adicionarSerie(serie);
		usu.adicionarSerie(serieService.buscarSerie(serie.getId()));
		clienteservice.cadastro(usu);
		System.out.println(usu.getSeries().toString());
		return new ResponseEntity<Serie>(serie, HttpStatus.OK);
	}

}
