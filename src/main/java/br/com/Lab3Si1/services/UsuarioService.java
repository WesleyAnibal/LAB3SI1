package br.com.Lab3Si1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuariorepositorio;
	
	public Usuario cadastro(Usuario usuario) {
		System.out.println(usuario.getNome());
		return usuariorepositorio.save(usuario);
	}
	
	public Usuario buscarUsuario(Long id){
		Usuario usu = usuariorepositorio.findOne(id);
		if(usu == null){
			return null;
		}
		else{
			return usu;
		}
	}
	
public boolean remover(Long id) {
		
		if(usuariorepositorio.exists(id)) {
			usuariorepositorio.delete(id);	
			return true;
		}
		return false;
		
		
	}
	
	public void salvarUsuario(Usuario usuario) {
		usuariorepositorio.save(usuario);
	}

}
