package br.com.Lab3Si1.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity(name="Usuario")
@Table(name="tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String nome;
	@Column
	private String email;
	@Column
	private String senha;
	@OneToMany
	//@ElementCollection
	private List<Serie> minhasSeries;
	@OneToMany
	//@ElementCollection
	private List<Serie> watchList;

	public Usuario() {}
	
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.minhasSeries = new ArrayList<>();
	}
	
	public List<Serie> getMinhasSeries() {
		return minhasSeries;
	}

	public void setMinhasSeries(List<Serie> minhasSeries) {
		this.minhasSeries = minhasSeries;
	}

	public List<Serie> getWatchList() {
		return watchList;
	}

	public void setWatchList(List<Serie> watchList) {
		this.watchList = watchList;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		for (Serie serie2 : minhasSeries) {
			if(serie2.equals(serie)) {
				//serie2.adicionarEP(temp, ep);
				return true;
			}
		}return false;
	}
	
	private Serie buscarserie(Long id) {
		for (Serie serie : minhasSeries) {
			if(serie.getId()== id) {
				return serie;
			}
		}return null;
	}
	
	public boolean removerSerie(Long i) {
		Serie serie = buscarserie(i);
		if(serie!= null) {
			return this.minhasSeries.remove(serie);
		}
		return false;
	}
	
	public boolean removerWatch(Long i) {
		Serie serie = buscarserie(i);
		if(serie!= null) {
			return this.watchList.remove(serie);
		}
		return false;
	}
	
	
	public boolean adicionarSerie(Serie serie) {
		if(this.minhasSeries == null) {
			this.minhasSeries = new ArrayList<>();
		}
		if(!this.minhasSeries.contains(serie)) {
			return this.minhasSeries.add(serie);
		}
		return false;
	}
	public boolean adicionarSerieWatch(Serie serie) {
		if(this.watchList == null) {
			this.watchList = new ArrayList<>();
		}
		if(!this.watchList.contains(serie)) {
			return this.watchList.add(serie);
		}
		return false;
	}
	
	public String toString() {
		return this.nome;
	}
}
