package br.com.Lab3Si1.services;

import java.net.URISyntaxException;
import java.util.List;

import org.eclipse.jetty.client.ResponseNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerUsuario {

	@Autowired
	UsuarioService clienteservice;

	@Autowired
	SerieService serieService;
	
	@CrossOrigin
	@RequestMapping(value="/busca",method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> buscaUsuario(@RequestBody Usuario usuario) {
		
		Usuario usu = this.getEmails(usuario);
		if (usu != null) {
			if(usuario.getSenha().equals(usu.getSenha())) {
				System.out.println(usu);
				return new ResponseEntity<>(usu, HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Usuario> deletarCliente(@PathVariable Long id) {

		if (clienteservice.remover(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@CrossOrigin
	@RequestMapping(value="/cadastro" , method = {RequestMethod.GET,RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		System.out.println(usuario.getId());
		if(this.getEmails(usuario) != null)
			return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
		return new ResponseEntity<>(clienteservice.cadastro(usuario), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.DELETE, value="{id}/minhasseries/{i}")
	public ResponseEntity<Serie> deleteMinhaSerie(@PathVariable Long id, @PathVariable Long i){
		Usuario usu = clienteservice.buscarUsuario(id);
		Serie serie = serieService.buscarSerie(i);
		if(usu.removerSerie(i)) {
			clienteservice.cadastro(usu);
			return new ResponseEntity<Serie>(serie, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.DELETE, value="{id}/watchlist/{i}")
	public ResponseEntity<Serie> deleteWatchList(@RequestBody Serie serie, @PathVariable Long id, @PathVariable Long i){
		Usuario usu = clienteservice.buscarUsuario(id);
		if(usu.removerWatch(i)) {
			clienteservice.cadastro(usu);
			return new ResponseEntity<Serie>(serie, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}/watched", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serie> adicionarSerie(@RequestBody Serie serie, @PathVariable("id") Long id) {
		Usuario usu = clienteservice.buscarUsuario(id);
		serieService.adicionarSerie(serie);
		if(usu.adicionarSerie(serieService.buscarSerie(serie.getId()))) {
			clienteservice.cadastro(usu);
			return new ResponseEntity<Serie>(serie, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);	
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.PUT, value="{id}/minhasseries/{i}")
	public ResponseEntity adicionarNota(@PathVariable("id") Long id, @PathVariable("i") Long i , @RequestBody String nota){
		Usuario usu = clienteservice.buscarUsuario(id);
		usu.adicionarNota(i, nota);
		clienteservice.cadastro(usu);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}/watch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Serie> adicionarSerieWatch(@RequestBody Serie serie, @PathVariable("id") Long id) {
		Usuario usu = clienteservice.buscarUsuario(id);
		serieService.adicionarSerie(serie);
		if(usu.adicionarSerieWatch(serieService.buscarSerie(serie.getId()))) {
			clienteservice.cadastro(usu);
			return new ResponseEntity<Serie>(serie, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
		
	}
	
	@CrossOrigin
	@RequestMapping(value="/{id}/minhasseries", method=RequestMethod.GET)
	public ResponseEntity<List<Serie>> getMinhasSeries(@PathVariable Long id){
		Usuario usu = clienteservice.buscarUsuario(id);
		System.out.println(usu);
		return new ResponseEntity<>(usu.getMinhasSeries(), HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@RequestMapping(value="/{id}/watchlist", method=RequestMethod.GET)
	public ResponseEntity<List<Serie>> getWatchList(@PathVariable Long id){
		Usuario usu = clienteservice.buscarUsuario(id);
		return new ResponseEntity<>(usu.getWatchList(), HttpStatus.ACCEPTED);
	}
	
	/*@CrossOrigin
	@RequestMapping(value="/email", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)*/
	public Usuario getEmails(Usuario user) {
		List<Usuario> l;
		Usuario usu = null;
		try {
			l = clienteservice.getUsuarioBy("email",user.getEmail());
			if(l.isEmpty())
				return null;
			usu = clienteservice.buscarUsuario(l.get(0).getId());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return usu;
	}
	
	@CrossOrigin
	@RequestMapping(value="/name", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getNames(@RequestBody Usuario user) {
		List<Usuario> l;
		Usuario usu = null;
		try {
			l = clienteservice.getUsuarioBy("NOME",user.getNome());
			if(l.isEmpty())
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			 usu = clienteservice.buscarUsuario(l.get(0).getId());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<>(usu, HttpStatus.ACCEPTED);
	}

}
