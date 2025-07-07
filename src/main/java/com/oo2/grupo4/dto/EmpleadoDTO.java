package com.oo2.grupo4.dto;


public record EmpleadoDTO(
    Long dni,     // heredado de Persona
    String nombre,      // heredado de Persona
    String apellido,    // heredado de Persona     
    String rol,
    int legajo,
    String Area
) {}
