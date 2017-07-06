package com.Lab3Si1.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity(name="Usuario")
@Table(name="tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Serie> getSeries() {
		return series;
	}

	public void setSeries(List<Serie> series) {
		this.series = series;
	}

	@Column
	private String nome;
	@Column
	private String email;
	@Column
	private String senha;
	
	@OneToMany
	private List<Serie> series;
	
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.series = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean adicionarEp(Serie serie, int ep, int temp) {
		for (Serie serie2 : series) {
			if(serie2.equals(serie)) {
				serie2.adicionarEP(temp, ep);
				return true;
			}
		}return false;
	}
	
	public boolean adicionarSerie(Serie serie) {
		if(!this.series.contains(serie)) {
			return this.series.add(serie);
		}
		return false;
	}
}
