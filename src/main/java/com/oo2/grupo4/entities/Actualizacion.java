package com.oo2.grupo4.entities;

import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString 
public class Actualizacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idActualizacion")
	private int idActualizacion; 
	
	private String contenido; 
	private LocalDate fechaActualizacion; 
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empleado_persona_idPersona")
	private Empleado empleado; 
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ticket_idTicket")
	private Ticket ticket;
	
	
	

}
