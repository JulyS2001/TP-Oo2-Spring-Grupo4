package com.oo2.grupo4.services.interfaces;

import java.util.Optional;

import com.oo2.grupo4.entities.Login;
import com.oo2.grupo4.entities.Persona;

public interface ILoginService {

	Login crearLogin(String correo, String contrasenia, Persona persona);
	
	boolean existsByCorreo(String correo);

	void save(Login login);

	Optional<Login> validarCredenciales(String correo);

}
