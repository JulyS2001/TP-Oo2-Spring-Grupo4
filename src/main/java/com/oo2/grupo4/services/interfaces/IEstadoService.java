package com.oo2.grupo4.services.interfaces;

import java.util.Optional;

import com.oo2.grupo4.entities.Estado;

public interface IEstadoService {
	
	Optional<Estado> findById(Integer id);
	Optional<Estado> findByTipo(String tipo);

}
