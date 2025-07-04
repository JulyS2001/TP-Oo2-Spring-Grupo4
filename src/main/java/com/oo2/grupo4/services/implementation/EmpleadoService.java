package com.oo2.grupo4.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.entities.Prioridad;
import com.oo2.grupo4.entities.Ticket;
import com.oo2.grupo4.entities.Area;
import com.oo2.grupo4.repositories.IEmpleadoRepository;
import com.oo2.grupo4.repositories.ITicketRepository;
import com.oo2.grupo4.services.interfaces.IEmpleadoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class EmpleadoService implements IEmpleadoService {

	private final PersonaService personaService;
	private final AreaService areaService;
	private final IEmpleadoRepository empleadoRepository;
	private final ITicketRepository ticketRepository;

	
	@Override
	public List<Empleado> getAll() {
		return empleadoRepository.findAll();
	}
	
	@Override
	public Empleado crearEmpleado(String nombre, String apellido, Long dni, Integer legajo, int idArea, String rol) {
		personaService.validarDniNoExiste(dni);

		Area area = areaService.traerPorId(idArea);

		Empleado empleado = new Empleado();
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setDni(dni);
		empleado.setLegajo(legajo);
		empleado.setRol(rol);
		empleado.setArea(area);

		return empleadoRepository.save(empleado);
	}

	@Override
	public int modificarEmpleado(int idPersona, String nombre, String apellido, Long dni, Integer legajo, int idArea, String rol) {
		
		Empleado empleado = this.traerPorId(idPersona);
		Area area = areaService.traerPorId(idArea);

		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setDni(dni);
		empleado.setLegajo(legajo);
		empleado.setArea(area);
		empleado.setRol(rol);

		return empleadoRepository.save(empleado).getIdPersona();
	}

	@Override
	public List<Empleado> obtenerEmpleadosPorArea(int idArea) {
		return empleadoRepository.findByAreaIdArea(idArea);
	}

	@Override 
	public List <Empleado> traerNoAdmi (String rol){
	    if (rol.equalsIgnoreCase("admin")) {
	        return empleadoRepository.findByRolNot("ADMIN");
	    }
	    return empleadoRepository.findByRol(rol);
	}
	
	@Override
	public Empleado traerPorId(int idPersona) {
		return empleadoRepository.findById(idPersona)
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado con id " + idPersona));
	}
	
	
	@Override
	public List<Ticket> getAllByEmpleadoId(int idEmpleado){
		List<Ticket> tickets = new ArrayList<>();
		
		for(Ticket t : ticketRepository.findAll()) {
			 if(t.getEmpleado() != null && t.getEmpleado().getIdPersona() == idEmpleado) {
			        tickets.add(t);
			    }
		}
		
		return tickets;
	}
	

	@Override
	public void delete(int idPersona) {
		empleadoRepository.deleteById(idPersona);
	}
}