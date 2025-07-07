package com.oo2.grupo4.dto;

import java.time.LocalDateTime;

public record ActualizacionCreateDTO(
		String contenido,
	    Integer idEmpleado,
	    Integer idTicket)
{}
