package com.oo2.grupo4.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Ticket;
import com.oo2.grupo4.repositories.ITicketRepository;
import com.oo2.grupo4.services.interfaces.ITicketService;

@Service
public class TicketService implements ITicketService{

	private final ITicketRepository ticketRepository;
	
	
	
	public TicketService(ITicketRepository ticketRepository) {
		super();
		this.ticketRepository = ticketRepository;
	}

	@Override
	public List<Ticket> getAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket getById(int id) {
		
		Optional<Ticket> ticket = ticketRepository.findById(id);
		
		return ticket.orElse(null);
	}

	@Override
	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@Override
	public void delete(int id) {
		ticketRepository.deleteById(id);
	}

	///

	@Override
	public List<Ticket> findByClienteIdPersona(int idCliente) {
		return ticketRepository.findByClienteIdPersona(idCliente);
	}

	@Override
	public List<Ticket> findByPrioridadTipo(String tipo) {
		// TODO Auto-generated method stub
		return ticketRepository.findByPrioridadTipo(tipo);
	}

	@Override
	public List<Ticket> findByEstadoTipo(String tipo) {
		// TODO Auto-generated method stub
		return findByEstadoTipo(tipo);
	}

	@Override
	public List<Ticket> findByTipoDeTicketTipo(String tipo) {
		return ticketRepository.findByTipoDeTicketTipo(tipo);
	}

	@Override
	public List<Ticket> findByTitulo(String titulo) {
		return ticketRepository.findByTitulo(titulo);
	}

	@Override
	public List<Actualizacion> getAllActualizacions() {
		return ticketRepository.getAllActualizacions();
	}

}
