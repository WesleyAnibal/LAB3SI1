package com.Lab3Si1.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity(name="Serie")
@Table(name="tb_serie")
public class Serie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String nome;
	
	@OneToMany
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
