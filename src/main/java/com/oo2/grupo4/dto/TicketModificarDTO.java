	package com.oo2.grupo4.dto;
	
	public record TicketModificarDTO (
			Integer idTicket,
			String titulo,
			String descripcion,
			Integer idTipoDeTicket,
			Integer idPrioridad,
			Integer idEstado
			){}
