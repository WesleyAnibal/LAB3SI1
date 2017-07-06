package com.Lab3Si1.demo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Temporada")
@Table(name="tb_temporada")
public class Temporada {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private int num;
	
	private Set<Integer> eps;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Temporada(int num) {
		this.eps = new HashSet<>();
		this.num = num;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Set<Integer> getEps() {
		return eps;
	}

	public void setEps(Set<Integer> eps) {
		this.eps = eps;
	}

	public void adicionarEp(int num) {
		this.eps.add(num);
	}
}
