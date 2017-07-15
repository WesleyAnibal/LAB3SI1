package br.com.Lab3Si1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Lab3Si1.services.Serie;
import br.com.Lab3Si1.services.SerieRepository;

@Service
public class SerieService {

	@Autowired
	SerieRepository serieRepository;
	
	public Serie adicionarSerie(Serie serie) {
		if(!serieRepository.exists(serie.getId()))
			this.serieRepository.save(serie);
		return serie;
	}
	
	public Serie buscarSerie(Long id) {
		Serie serie = this.serieRepository.findOne(id);
		return serie;
	}
	
	
}
