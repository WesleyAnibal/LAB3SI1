package com.Lab3Si1.demo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String nome;
	private String ID;
	private String senha;
	private List<Serie> series;
	
	public Usuario(String nome, String ID, String senha) {
		this.nome = nome;
		this.ID = ID;
		this.senha = senha;
		this.series = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
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
