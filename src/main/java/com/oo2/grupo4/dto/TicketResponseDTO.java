package com.oo2.grupo4.dto;

import java.time.LocalDateTime;

public record TicketResponseDTO (
	
	 Integer idTicket,
	 String titulo,
	 String descripcion,
	 LocalDateTime fechaCreacion,
	 LocalDateTime fechaCierre,
	 String nombrePersona,
	 String apellidoPersona,
	 String estado,
	 String prioridad,
	 String tipoDeTicket,
	 String nombreEmpleado, 
	 String apellidoEmpleado
) {}
