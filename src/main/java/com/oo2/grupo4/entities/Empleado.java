package com.oo2.grupo4.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class Empleado extends Persona{

	private String rol;
	private long legajo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idArea")
	private Area area;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idActualizacion")
	private List<Actualizacion> lstActualizacion;
	
}
