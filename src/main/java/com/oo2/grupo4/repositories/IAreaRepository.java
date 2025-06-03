package com.oo2.grupo4.repositories;

import com.oo2.grupo4.entities.Area;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IAreaRepository extends JpaRepository<Area, Integer>{
	
	Optional<Area> findByNombre(String nombre);

}
