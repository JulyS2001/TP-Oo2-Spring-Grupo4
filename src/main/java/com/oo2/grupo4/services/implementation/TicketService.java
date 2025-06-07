package com.oo2.grupo4.services.implementation;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Estado;
import com.oo2.grupo4.entities.Prioridad;
import com.oo2.grupo4.entities.TipoDeTicket;
import com.oo2.grupo4.entities.Ticket;
import com.oo2.grupo4.repositories.IEmpleadoRepository;
import com.oo2.grupo4.repositories.ITicketRepository;
import com.oo2.grupo4.services.interfaces.ITicketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService{

	private final ITicketRepository ticketRepository;
	private final EstadoService estadoService; 
	private final PrioridadService prioridadService; 
	private final TipoDeTicketService tipoDeTicketService;
	
	
	
	@Override
	public Ticket crearTicket(String titulo, String descripcion, Integer idTipoDeTicket, Integer idPrioridad, Integer idEstado) {
		
		TipoDeTicket tipoDeTicket = tipoDeTicketService.findById(idTipoDeTicket);
		Estado estado = estadoService.findById(idEstado);
		Prioridad prioridad = prioridadService.findById(idPrioridad);
		
		Ticket ticket = new Ticket();
		ticket.setTitulo(titulo);
		ticket.setDescripcion(descripcion);
		ticket.setFechaCreacion(LocalDate.now());
		ticket.setFechaCierre(null);
		ticket.setPrioridad(prioridad);
		ticket.setEstado(estado);
		ticket.setTipoDeTicket(tipoDeTicket);
		
		return ticketRepository.save(ticket);
		
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
	
	public boolean existsByTitulo(String titulo) {
		return ticketRepository.existsByTitulo(titulo);
	}
	
	

	@Override
	public List<Actualizacion> getAllActualizacions(int idTicket) {
		Optional<Ticket> ticket = ticketRepository.findById(idTicket);
	    return ticket.map(Ticket::getActualizaciones).orElse(Collections.emptyList());
	}
	
}
