package br.com.Lab3Si1.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Serie")
//@Table(name="tb_serie")
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((temporadas == null) ? 0 : temporadas.hashCode());
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
		if (temporadas == null) {
			if (other.temporadas != null)
				return false;
		} else if (!temporadas.equals(other.temporadas))
			return false;
		return true;
	}

	@Column
	private String nome;
	@Column
	private String notaIMDB;
	@Column
	private String notaUsuario;
	@Column
	private String descricao;
	@Column
	private String linkImage;
	@Column
	private String imdbRating;
	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	@ElementCollection
	private List<Temporada> temporadas;

	public Serie() {}

	public Serie(String nome, int numeroTemps) {
		this.nome = nome;
		this.temporadas = new ArrayList<>();
		adicionaTemps(numeroTemps);
	}

	public String getNotaIMDB() {
		return notaIMDB;
	}

	public void setNotaIMDB(String notaIMDB) {
		this.notaIMDB = notaIMDB;
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

	public List<Temporada> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(List<Temporada> temporadas) {
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

	public void adicionarEP(int temp, int ep) {
		for (Temporada temporada : temporadas) {
			if (temporada.getNum() == temp) {
				temporada.adicionarEp(ep);
			}
		}
	}

	private void adicionaTemps(int num) {
		for (int i = 1; i <= num; i++) {
			this.temporadas.add(new Temporada(i));
		}
	}
}
