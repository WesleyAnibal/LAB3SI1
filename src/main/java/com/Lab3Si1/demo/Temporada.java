package com.Lab3Si1.demo;

import java.util.HashSet;
import java.util.Set;

public class Temporada {
	private int num;
	private Set<Integer> eps;
	
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
