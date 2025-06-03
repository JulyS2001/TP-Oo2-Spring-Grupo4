package com.oo2.grupo4.repositories;

import com.oo2.grupo4.entities.Prioridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrioridadRepository extends JpaRepository<Prioridad, Integer>{
	
	Prioridad findByTipo(String tipo); 

}
