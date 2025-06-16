package com.oo2.grupo4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oo2.grupo4.entities.Empleado;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Integer> {

	List<Empleado> findByAreaIdArea(int idArea); // Buscar empleados por id del área

	Empleado findByLegajo(Integer legajo); // Buscar por legajo
}
