package com.oo2.grupo4.services.interfaces;

import java.util.Optional;

import com.oo2.grupo4.entities.TipoDeTicket;

public interface ITipoDeTicketService {

	Optional<TipoDeTicket> findById(Integer id);
	Optional<TipoDeTicket> findByTipo(String tipo);
}
