package com.oo2.grupo4.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo4.entities.Cliente;
import com.oo2.grupo4.entities.Contacto;
import com.oo2.grupo4.entities.Login;
import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.services.implementation.ClienteService;
import com.oo2.grupo4.services.implementation.ContactoService;
import com.oo2.grupo4.services.implementation.LoginService;
import com.oo2.grupo4.services.implementation.PersonaService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IniciosController {

	private final PersonaService personaService;
	private final LoginService loginService;
	private final ClienteService clienteService;
	private final ContactoService contactoService;
	private final PasswordEncoder passwordEncoder;
	
	 	@GetMapping("/admin/inicio")
	    public String vistaAdmin() {
	        return "admin/inicioAdmin"; 
	    }

	    @GetMapping("/empleado/inicio")
	    public String vistaEmpleado() {
	        return "empleado/inicioEmpleado";
	    }

	    @GetMapping("/cliente/inicio")
	    public String vistaCliente() {
	        return "cliente/inicioCliente";
	    }
}
