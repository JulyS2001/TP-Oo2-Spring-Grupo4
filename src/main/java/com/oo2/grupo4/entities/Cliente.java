package com.oo2.grupo4.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class Cliente extends Persona{
	
	private String nroCliente;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idTicket")
	private List<Ticket> lstTicket;
}
