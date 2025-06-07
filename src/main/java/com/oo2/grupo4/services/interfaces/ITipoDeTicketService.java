package com.oo2.grupo4.services.interfaces;

import java.util.Optional;

import com.oo2.grupo4.entities.TipoDeTicket;

public interface ITipoDeTicketService {

	TipoDeTicket findById(int id);
	TipoDeTicket findByTipo(String tipo);
}
