package com.oo2.grupo4.dto;

public record ClienteResponseDTO (
		
		int idPersona,
		String nombre,
		String apellido,
	    Long dni,
	    int nroCliente
) {}
