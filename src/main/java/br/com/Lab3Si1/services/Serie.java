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