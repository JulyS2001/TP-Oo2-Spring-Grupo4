package com.oo2.grupo4.services.implementation;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Cliente;
import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.entities.Estado;
import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.entities.Prioridad;
import com.oo2.grupo4.entities.TipoDeTicket;
import com.oo2.grupo4.exceptions.DescripcionMuyCortaException;
import com.oo2.grupo4.exceptions.EmpleadoConMuchosTicketsException;
import com.oo2.grupo4.entities.Ticket;
import com.oo2.grupo4.repositories.ITicketRepository;
import com.oo2.grupo4.services.interfaces.ITicketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService {

	private final ITicketRepository ticketRepository;
	private final EstadoService estadoService;
	private final PrioridadService prioridadService;
	private final TipoDeTicketService tipoDeTicketService;
	private final EmpleadoService empleadoService;
	private final ClienteService clienteService;


	@Override
	public Ticket crearTicket(String titulo, String descripcion, int idTipoDeTicket, Integer idCliente) {
		

		if (descripcion == null || descripcion.length() < 30) {
		    throw new DescripcionMuyCortaException("La descripción debe tener al menos 30 caracteres.");
		}

		Estado estado = estadoService.findById(1);
		Prioridad prioridad = prioridadService.findById(2);
		Empleado empleado = empleadoService.traerPorId(1);
		Cliente cliente = clienteService.traerPorId(idCliente);
		TipoDeTicket tipoDeTicket = tipoDeTicketService.findById(idTipoDeTicket);


		Ticket ticket = new Ticket();
		ticket.setTitulo(titulo);
		ticket.setDescripcion(descripcion);
		ticket.setFechaCreacion(LocalDateTime.now());
		ticket.setEstado(estado);
		ticket.setPrioridad(prioridad);
		ticket.setEmpleado(empleado);
		ticket.setCliente(cliente);
		ticket.setTipoDeTicket(tipoDeTicket);

		return ticketRepository.save(ticket);

	}

	public int modificarTicket(int idTicket, Integer idTipoDeTicket, Integer idPrioridad, Integer idEstado) {
		Ticket ticket = this.getById(idTicket);

		TipoDeTicket tipoDeTicket = tipoDeTicketService.findById(idTipoDeTicket);
		Estado estado = estadoService.findById(idEstado);
		Prioridad prioridad = prioridadService.findById(idPrioridad);

		if (estado.getIdEstado() == 2) {
			ticket.setFechaCierre(LocalDateTime.now());
		} else {
			ticket.setFechaCierre(null);
		}

		ticket.setPrioridad(prioridad);
		ticket.setEstado(estado);
		ticket.setTipoDeTicket(tipoDeTicket);

		return ticketRepository.save(ticket).getIdTicket();

	}

	@Override
	public Ticket cambiarEmpleado(int idTicket, int idEmpleado) {
		
	    Empleado nuevoEmpleado = empleadoService.traerPorId(idEmpleado);
		
	    // Contar los tickets abiertos (estado id = 1) del nuevo empleado
	    long ticketsAbiertos = nuevoEmpleado.getTickets().stream()
	        .filter(ticket -> ticket.getEstado().getIdEstado() == 1)
	        .count();
	    
		if (ticketsAbiertos >= 4 ) {
			throw new EmpleadoConMuchosTicketsException ("El empleado no puede recibir más tickets en este momento");
		}
		
	    Ticket ticket = this.getById(idTicket);

	    ticket.setEmpleado(nuevoEmpleado);

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
