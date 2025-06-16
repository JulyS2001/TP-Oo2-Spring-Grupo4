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
public class AuthController {

	private final PersonaService personaService;
	private final LoginService loginService;
	private final ClienteService clienteService;
	private final ContactoService contactoService;
	private final PasswordEncoder passwordEncoder;

	@GetMapping("/registro")
	public ModelAndView vistaRegistro(@RequestParam(required = false) String mensaje) {
		ModelAndView mav = new ModelAndView("registro/registroCliente");
		mav.addObject("error", mensaje);
		return mav;
	}

	@PostMapping("/registro")
	public ModelAndView registrarCliente(@RequestParam String nombre, @RequestParam String apellido,
			@RequestParam long dni, @RequestParam String nroCliente) {
		Cliente personaCliente = new Cliente();
		personaCliente.setNombre(nombre);
		personaCliente.setApellido(apellido);
		personaCliente.setDni(dni);
		personaCliente.setNroCliente(nroCliente);

		personaService.save(personaCliente);

		return new ModelAndView("redirect:/completarContacto?idPersona=" + personaCliente.getIdPersona());
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

		if (persona == null) {
			RedirectView redirect = new RedirectView("/registro");
			redirect.addStaticAttribute("mensaje", "Persona no encontrada");
			return new ModelAndView(redirect);
		}

		if (contactoService.existeEmail(correo)) {
			RedirectView redirect = new RedirectView("/completarContacto");
			redirect.addStaticAttribute("idPersona", idPersona);
			redirect.addStaticAttribute("mensaje", "El correo ya está en uso");
			return new ModelAndView(redirect);
		}

		Login login = new Login();
		login.setCorreo(correo);
		login.setContrasenia(passwordEncoder.encode(contrasenia));
		login.setPersona(persona);
		loginService.save(login);

		Contacto contacto = new Contacto();
		contacto.setEmail(correo);
		contacto.setTelefono(telefono);
		contacto.setPersona(persona);
		contactoService.save(contacto);

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
