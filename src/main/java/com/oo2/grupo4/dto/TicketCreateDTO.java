package com.oo2.grupo4.dto;

public record TicketCreateDTO(
		
		 String titulo,
		 String descripcion,
		 int idTipoDeTicket,
		 Integer idCliente
		) {}
