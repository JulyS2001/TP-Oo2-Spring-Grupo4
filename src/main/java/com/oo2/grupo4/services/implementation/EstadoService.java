package com.oo2.grupo4.services.implementation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Estado;
import com.oo2.grupo4.repositories.IEstadoRepository;
import com.oo2.grupo4.services.interfaces.IEstadoService;

import lombok.AllArgsConstructor;

@Service
public class EstadoService implements IEstadoService {
	
	private final IEstadoRepository estadoRepository;
	
	public EstadoService(IEstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;
	}
	
	 @Override
	    public Optional<Estado> findById(Integer id) {
	        return estadoRepository.findById(id);
	    }

	    @Override
	    public Optional<Estado> findByTipo(String tipo) {
	        return estadoRepository.findByTipo(tipo);
	    }
	
	

}
