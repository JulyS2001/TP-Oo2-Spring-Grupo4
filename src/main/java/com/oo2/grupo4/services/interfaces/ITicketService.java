package com.oo2.grupo4.services.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Ticket;


public interface ITicketService {

	Ticket crearTicket(String titulo, String descripcion, int idTipoDeTicket, Integer idCliente);

	public Ticket cambiarEmpleado(int idTicket, int idEmpleado);
	
	public int modificarTicket(int idTicket, Integer idTipoDeTicket, Integer idPrioridad, Integer idEstado);

	List<Ticket> getAll();

	Ticket getById(int id);

	Ticket save(Ticket ticket);

	void delete(int id);

	List<Ticket> findByClienteIdPersona(int idCliente);

	List<Ticket> findByPrioridadTipo(String tipo);

	List<Ticket> findByEstadoTipo(String tipo);

	List<Ticket> findByTipoDeTicketTipo(String tipo);

	List<Ticket> findByTitulo(String titulo);

	List<Actualizacion> getAllActualizacions(int Ticket);

	boolean existsByTitulo(String titulo);

}
