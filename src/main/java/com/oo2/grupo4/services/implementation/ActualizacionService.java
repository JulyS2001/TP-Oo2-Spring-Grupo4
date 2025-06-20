package com.oo2.grupo4.services.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.exceptions.TicketSinActualizaciones;
import com.oo2.grupo4.repositories.IActualizacionRepository;
import com.oo2.grupo4.services.interfaces.IActualizacionService;

@Service
public class ActualizacionService implements IActualizacionService {

	private final IActualizacionRepository actualizacionRepository;

	public ActualizacionService(IActualizacionRepository iActualizacionRepository) {
		this.actualizacionRepository = iActualizacionRepository;
	}

	@Override
	public List<Actualizacion> getAll() {

		return actualizacionRepository.findAll();
	}

	@Override
	public Actualizacion getById(int id) {
		Optional<Actualizacion> act = actualizacionRepository.findById(id);
		return act.orElse(null);
	}

	@Override
	public Actualizacion save(Actualizacion actualizacion) {
		return actualizacionRepository.save(actualizacion);
	}

	///
	
	
	@Override
	public Optional<Actualizacion> findByIdActualizacion(int idActualizacion) {
		return actualizacionRepository.findByIdActualizacion(idActualizacion);
	}

	@Override
	public List<Actualizacion> findByTicketIdTicket(int idTicket) {
		return actualizacionRepository.findByTicketIdTicket(idTicket);
	}

	@Override
	public List<Actualizacion> findByEmpleadoIdPersona(int idEmpleado) {
		return actualizacionRepository.findByEmpleadoIdPersona(idEmpleado);
	}

	@Override
	public List<Actualizacion> findByFechaActualizacion(LocalDateTime fechaActualizacion) {
		return actualizacionRepository.findByFechaActualizacion(fechaActualizacion);

	}
	@Override
	public List<Actualizacion> getAllByTicketId(int idTicket){
		List<Actualizacion> actualizaciones = new ArrayList<>();
		
		for(Actualizacion a : actualizacionRepository.findAll()) {
			if(a.getTicket().getIdTicket()==idTicket) {
				actualizaciones.add(a);
			}
		}
		if(actualizaciones.isEmpty()) {
			throw new TicketSinActualizaciones(idTicket);
		}
		
		return actualizaciones;
	}

	@Override
	public void delete(int idTicket, int idActualizacion) {
		Actualizacion actualizacion = null;
		for(Actualizacion a : actualizacionRepository.findAll()) {
			if(a.getTicket().getIdTicket()==idTicket && a.getIdActualizacion()==idActualizacion) {
				actualizacion = a;
			}
		}
		
		actualizacionRepository.delete(actualizacion);
	}
	
}
