package com.oo2.grupo4.services.interfaces;

import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.entities.Ticket;

import java.util.List;

public interface IEmpleadoService {

	List<Empleado> getAll();
	
	Empleado crearEmpleado(String nombre, String apellido, Long dni, Integer legajo, int idArea, String rol);

	public int modificarEmpleado(int idPersona, String nombre, String apellido, Long dni, Integer legajo, int idArea, String rol);

	List<Empleado> obtenerEmpleadosPorArea(int idArea);
	
	List <Empleado> traerNoAdmi (String rol);

	Empleado traerPorId(int idPersona);
	
	public List<Ticket> getAllByEmpleadoId(int idEmpleado);
	
	public void delete(int idPersona);
}