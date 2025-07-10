package com.oo2.grupo4.dto;

public record EmpleadoDTO (
		int idPersona,
		String nombre,
		String apellido,
	    Long dni,
	    int legajo,
	    int idArea,
	    String area, // ← nombre del área para mostrar
	    String rol
    ){}
