package com.oo2.grupo4.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.oo2.grupo4.entities.Empleado;

import java.io.IOException;
import java.util.Collection;

@Component
public class RedireccionPorRolHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    Authentication authentication) throws IOException, ServletException {

		// Obtenemos el usuario autenticado
		Object principal = authentication.getPrincipal();

		String redirectUrl = "/"; // Redirección por defecto

		if (principal instanceof UserDetailsImpl userDetails) {
			Object persona = userDetails.getLogin().getPersona();

			if (persona instanceof Empleado empleado) {
				// Verificamos el rol del empleado
				if ("ADMIN".equalsIgnoreCase(empleado.getRol())) {
					redirectUrl = "/admin/inicio"; // Vista exclusiva para admin
				} else {
					redirectUrl = "/empleado/inicio"; // Vista general para empleados
				}
			} else {
				// Si no es Empleado, asumimos que es Cliente
				redirectUrl = "/cliente/inicio";
			}
		}

		response.sendRedirect(redirectUrl);
	}
}