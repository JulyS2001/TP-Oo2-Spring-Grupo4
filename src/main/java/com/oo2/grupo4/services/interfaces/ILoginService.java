package com.oo2.grupo4.services.interfaces;

import java.util.Optional;

import com.oo2.grupo4.entities.Login;

public interface ILoginService {
	
	
   /* Optional<Login> registrarLogin(String correo, String contrasenia, Persona persona);

    Login login(String correo, String contrasenia) throws Exception;

    Login actualizarContrasenia(int idLogin, String nuevaContrasenia);

    boolean existeCorreo(String correo);
    */
    
    
    boolean existsByCorreo(String correo);
    void save(Login login);
    Optional<Login> validarCredenciales(String correo);

}
