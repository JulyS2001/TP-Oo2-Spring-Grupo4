package com.oo2.grupo4.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.repositories.IActualizacionRepository;
import com.oo2.grupo4.services.interfaces.IActualizacionService;

@Service
public class ActualizacionService implements IActualizacionService{

    private final IActualizacionRepository actualizacionRepository ;
    
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

	@Override
	public void delete(int id) {
		actualizacionRepository.deleteById(id);
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
	public List<Actualizacion> findByFechaActualizacion(LocalDate fechaActualizacion) {
		return actualizacionRepository.findByFechaActualizacion(fechaActualizacion);
		
	}	
	
}
