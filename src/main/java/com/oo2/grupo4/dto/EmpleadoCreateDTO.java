package com.oo2.grupo4.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmpleadoCreateDTO(
		@NotBlank String nombre,      // heredado de Persona
		@NotBlank String apellido,    // heredado de Persona    
		@NotNull @Min(1) Long dni,     		// heredado de Persona
		@NotNull int legajo,
		@NotNull int idArea,
		@NotBlank String rol
) {}
