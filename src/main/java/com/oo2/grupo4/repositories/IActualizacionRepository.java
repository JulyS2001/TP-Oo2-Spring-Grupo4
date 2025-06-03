package com.oo2.grupo4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oo2.grupo4.entities.Actualizacion;
import java.util.List;


@Repository
public interface IActualizacionRepository extends JpaRepository<Actualizacion, Integer> {

	List<Actualizacion> findByTicketIdTicket (int idTicket);
	
	List<Actualizacion> findByEmpleadoIdPersona(int idEmpleado);
	
}
