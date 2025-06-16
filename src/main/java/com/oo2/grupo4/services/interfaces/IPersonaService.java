package com.oo2.grupo4.services.interfaces;

import com.oo2.grupo4.entities.Persona;

public interface IPersonaService {

	Persona save(Persona persona);

	Persona traerPorId(int idPersona);

	Persona traerPorDni(Long dni);

	int actualizarPersona(int idPersona, String nombre, String apellido, Long dni);

	void eliminarPersona(int idPersona);

	void validarDniNoExiste(Long dni);
}
