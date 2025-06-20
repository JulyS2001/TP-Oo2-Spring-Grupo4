package com.oo2.grupo4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo4.entities.Cliente;
import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.exceptions.DniExistente;
import com.oo2.grupo4.exceptions.MailExistente;
import com.oo2.grupo4.services.implementation.ClienteService;
import com.oo2.grupo4.services.implementation.ContactoService;
import com.oo2.grupo4.services.implementation.LoginService;
import com.oo2.grupo4.services.implementation.PersonaService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

	private final PersonaService personaService;
	private final LoginService loginService;
	private final ClienteService clienteService;
	private final ContactoService contactoService;

	@GetMapping("/registro")
	public ModelAndView vistaRegistro(@RequestParam(required = false) String mensaje) {
		ModelAndView mav = new ModelAndView("registro/registroCliente");
		mav.addObject("error", mensaje);
		return mav;
	}

	@PostMapping("/registro")
	public ModelAndView registrarCliente(@RequestParam String nombre, @RequestParam String apellido,
			@RequestParam long dni, @RequestParam String nroCliente) {

		
		if(personaService.existsByDni(dni)) {
			throw new DniExistente("El dni ingresado ya existe.");
		}
		
		Cliente cliente = clienteService.crearCliente(nombre, apellido, dni, nroCliente);
		
		return new ModelAndView("redirect:/completarContacto?idPersona=" + cliente.getIdPersona());
	}

	@GetMapping("/completarContacto")
	public ModelAndView completarContacto(@RequestParam int idPersona, @RequestParam(required = false) String mensaje) {
		ModelAndView mav = new ModelAndView("registro/completarContacto");
		mav.addObject("idPersona", idPersona);
		mav.addObject("error", mensaje);
		return mav;
	}

	@PostMapping("/completarContacto")
	public ModelAndView completarRegistro(@RequestParam int idPersona, @RequestParam String telefono,
			@RequestParam String correo, @RequestParam String contrasenia) {

		Persona persona = personaService.traerPorId(idPersona);
		
		
		if(loginService.existsByCorreo(correo)) {
			throw new MailExistente("El correo ingresado ya esta en uso.");
		}
		
		loginService.crearLogin(correo, contrasenia, persona);

		contactoService.crearContacto(telefono, contrasenia, persona);

		return new ModelAndView("redirect:/login");
	}

	@GetMapping("/login")
	public ModelAndView mostrarLogin(@RequestParam(required = false) String error,
			@RequestParam(required = false) String logout) {
		ModelAndView mav = new ModelAndView("registro/login");

		if (error != null) {
			mav.addObject("mensaje", "Credenciales incorrectas");
		}
		if (logout != null) {
			mav.addObject("mensaje", "Sesión cerrada con éxito");
		}

		return mav;
	}
}
