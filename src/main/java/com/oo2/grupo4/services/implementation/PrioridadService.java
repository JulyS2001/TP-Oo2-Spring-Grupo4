package com.oo2.grupo4.services.implementation;

import org.springframework.stereotype.Service;
import java.util.Optional;

import com.oo2.grupo4.entities.Prioridad;
import com.oo2.grupo4.repositories.IPrioridadRepository;
import com.oo2.grupo4.services.interfaces.IPrioridadService;

@Service
public class PrioridadService implements IPrioridadService {
	
	private final IPrioridadRepository prioridadRepository;
	
	public PrioridadService(IPrioridadRepository prioridadRepository) {
		this.prioridadRepository = prioridadRepository;
	}
	
	 @Override
	    public Optional<Prioridad> findById(Integer id) {
	        return prioridadRepository.findById(id);
	    }

	    @Override
	    public Optional<Prioridad> findByTipo(String tipo) {
	        return prioridadRepository.findByTipo(tipo);
	    }

}
