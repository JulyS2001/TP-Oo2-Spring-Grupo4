package com.oo2.grupo4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oo2.grupo4.entities.Contacto;

public interface IContactoRepository extends JpaRepository<Contacto,Integer> {

}
