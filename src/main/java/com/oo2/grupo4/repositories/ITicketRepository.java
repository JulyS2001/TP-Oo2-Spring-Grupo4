package com.oo2.grupo4.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Ticket;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Integer>{

	Optional<Ticket> findById(Integer id);
	
	List<Ticket> findByClienteIdPersona(int idCliente);
	
	@Query("SELECT t.actualizaciones FROM Ticket t WHERE t.idTicket = :idTicket")
	List<Actualizacion> findActualizacionesByTicketId(@Param("idTicket") int idTicket);
	
	List<Ticket> findByPrioridadTipo(String tipo);
	
	List<Ticket> findByEstadoTipo(String tipo);
	
	List<Ticket> findByTipoDeTicketTipo(String tipo);
	
	List<Ticket> findByFechaCreacion(LocalDate fecha);
	
}
