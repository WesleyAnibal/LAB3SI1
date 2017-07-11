package com.Lab3Si1.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Serie")
public class Serie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public String getNotaIMDB() {
		return notaIMDB;
	}

	public void setNotaIMDB(String notaIMDB) {
		this.notaIMDB = notaIMDB;
	}

	public String getDescrição() {
		return descrição;
	}

	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}

	public String getLinkImage() {
		return linkImage;
	}

	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
	}

	@Column
	private String nome;
	@Column
	private String notaIMDB;
	@Column
	private String descrição;
	@Column
	private String linkImage;
	@ElementCollection
	private List<Temporada> temporadas;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Serie(String nome, int numeroTemps) {
		this.nome = nome;
		this.temporadas = new ArrayList<>();
		adicionaTemps(numeroTemps);
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

	public void adicionarEP(int temp, int ep) {
		for (Temporada temporada : temporadas) {
			if(temporada.getNum() == temp) {
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
