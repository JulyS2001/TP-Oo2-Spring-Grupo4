package com.oo2.grupo4.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTicket;
	private String titulo;
	private String descripcion;
	private LocalDate fechaCreacion;
	private LocalDate fechaCierre;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tipo_ticket_id")
	private TipoDeTicket tipoDeTicket;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prioridad_id")
	private Prioridad prioridad;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id_persona")
	private Cliente cliente;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "estado_id")
	private Estado estado;
	
	@OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
	private List<Actualizacion> actualizaciones;
	
}
