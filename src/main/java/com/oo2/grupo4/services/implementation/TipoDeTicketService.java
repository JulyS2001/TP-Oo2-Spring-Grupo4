package com.oo2.grupo4.services.implementation;

import org.springframework.stereotype.Service;
import java.util.Optional;


import com.oo2.grupo4.entities.TipoDeTicket;
import com.oo2.grupo4.repositories.ITipoDeTicketRepository;
import com.oo2.grupo4.services.interfaces.ITipoDeTicketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class TipoDeTicketService implements ITipoDeTicketService {

	private final ITipoDeTicketRepository tipoDeTicketRepository;

	@Override
	public Optional<TipoDeTicket> findById(Integer id) {
		return tipoDeTicketRepository.findById(id);
	}

	@Override
	public Optional<TipoDeTicket> findByTipo(String tipo) {
		return tipoDeTicketRepository.findByTipo(tipo);
	}

}
