package br.com.Lab3Si1.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Lab3Si1.services.Usuario;
import br.com.Lab3Si1.services.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuariorepositorio;

	public Usuario cadastro(Usuario usuario) {
		System.out.println(usuario.getNome());
		return usuariorepositorio.save(usuario);
	}

	public Usuario buscarUsuario(Long id) {
		Usuario usu = usuariorepositorio.findOne(id);
		if (usu == null) {
			return null;
		} else {
			return usu;
		}
	}

	public boolean remover(Long id) {

		if (usuariorepositorio.exists(id)) {
			usuariorepositorio.delete(id);
			return true;
		}
		return false;

	}

	public void salvarUsuario(Usuario usuario) {
		usuariorepositorio.save(usuario);
	}
	
	public List<Usuario> getUsuarioBy(String by,String search) {
		String sql = "SELECT * FROM TB_USUARIO WHERE "+by+" = ?";
		List<Usuario> usuarios = new ArrayList<>();
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:h2:file:~/h2/app", "sa", "");
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, search);
			
			ResultSet executeQuery = stmt.executeQuery();
			
			while (executeQuery.next()) {
				Long id = executeQuery.getLong("ID");
				String nome = executeQuery.getString("NOME");
				String senha = executeQuery.getString("SENHA");
				String email = executeQuery.getString("EMAIL");
				Usuario u = new Usuario(nome, email, senha);
				u.setId(id);
				usuarios.add(u);
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}

}
