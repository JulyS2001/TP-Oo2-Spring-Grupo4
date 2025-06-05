package com.oo2.grupo4.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@PrimaryKeyJoinColumn(name = "persona_idPersona") // Hereda la PK de Persona
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Empleado extends Persona{

	private String rol;
	private long legajo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "area_id_area")
	private Area area;
	
	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
	private List<Actualizacion> actualizaciones;
	
}
