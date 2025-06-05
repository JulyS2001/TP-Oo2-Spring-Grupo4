package com.oo2.grupo4.repositories;

import java.util.List;


import com.oo2.grupo4.entities.Empleado;

public interface IEmpleadoRepository {

	  List<Empleado> findByAreaIdArea(int idArea); // Buscar empleados por id del área
	  Empleado findByLegajo(String legajo);        // Buscar por legajo
}
