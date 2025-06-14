package com.oo2.grupo4.services.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.oo2.grupo4.entities.Actualizacion;

public interface IActualizacionService {

	List<Actualizacion> getAll();

	Actualizacion getById(int id);

	Actualizacion save(Actualizacion actualizacion);

	void delete(int id);

	// en interface

	Optional<Actualizacion> findByIdActualizacion(int idActualizacion);

	List<Actualizacion> findByTicketIdTicket(int idTicket);

	List<Actualizacion> findByEmpleadoIdPersona(int idEmpleado);

	List<Actualizacion> findByFechaActualizacion(LocalDateTime fechaActualizacion);

}
