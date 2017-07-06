package com.Lab3Si1.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuariorepositorio;
	
	public boolean cadastro(Usuario usuario) {
		usuariorepositorio.save(usuario);
		return true;
	}
	
	public Usuario buscarUsuario(Usuario usuario){
		Usuario usu = usuariorepositorio.findOne(usuario.getEmail());
		if(usu == null){
			return null;
		}
		else if(usuario.getSenha().equals(usu.getSenha())){
			return usu;
		}
		return null;
	}

}
