package br.com.Lab3Si1.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="Serie")
@Table(name="tb_serie")
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		//result = prime * result + ((temporadas == null) ? 0 : temporadas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Serie other = (Serie) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Column
	@JsonProperty(value="Title")
	private String nome;
	@Column
	private String notaUsuario;
	@Column
	@JsonProperty(value="Plot")
	private String descricao;
	@Column
	@JsonProperty(value="Poster")
	private String linkImage;
	@Column
	private String imdbRating;
	@Column
	@JsonProperty(value="Rated")
	private String rated;
	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	@Column
	@JsonProperty(value="totalSeasons")
	private String temporadas;

	public Serie() {}

	public Serie(String nome, String numeroTemps) {
		this.nome = nome;
		this.temporadas = numeroTemps;
	}

	public String getLinkImage() {
		return linkImage;
	}

	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
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

	public String getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(String temporadas) {
		this.temporadas = temporadas;
	}
	public String getNotaUsuario() {
		return notaUsuario;
	}

	public void setNotaUsuario(String notaUsuario) {
		this.notaUsuario = notaUsuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
