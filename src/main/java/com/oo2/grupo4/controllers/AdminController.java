package com.oo2.grupo4.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Contacto;
import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.entities.Login;
import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.entities.Ticket;
import com.oo2.grupo4.exceptions.DescripcionMuyCortaException;
import com.oo2.grupo4.exceptions.DniExistente;
import com.oo2.grupo4.exceptions.EmpleadoConMuchosTicketsException;
import com.oo2.grupo4.exceptions.MailExistente;
import com.oo2.grupo4.services.implementation.ActualizacionService;
import com.oo2.grupo4.services.implementation.AreaService;
import com.oo2.grupo4.services.implementation.ClienteService;
import com.oo2.grupo4.services.implementation.ContactoService;
import com.oo2.grupo4.services.implementation.EmpleadoService;
import com.oo2.grupo4.services.implementation.EstadoService;
import com.oo2.grupo4.services.implementation.LoginService;
import com.oo2.grupo4.services.implementation.PersonaService;
import com.oo2.grupo4.services.implementation.PrioridadService;
import com.oo2.grupo4.services.implementation.TicketService;
import com.oo2.grupo4.services.implementation.TipoDeTicketService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	private final ActualizacionService actualizacionService;
	private final EmpleadoService empleadoService;
	private final ClienteService clienteService;
	private final PersonaService personaService;
	private final TicketService ticketService;
	private final TipoDeTicketService tipodeticketservice;
	private final EstadoService estadoService;
	private final PrioridadService prioridadService;
	private final ContactoService contactoService;
	private final LoginService loginService;
	private final AreaService areaService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/asignarEmpleado")
	public ModelAndView vistaAsignarEmpleado(@RequestParam int idTicket) {
		ModelAndView mav = new ModelAndView("admin/asignarEmpleado");
		mav.addObject("empleados", empleadoService.traerNoAdmi("admin"));
		mav.addObject("idTicket", idTicket);
		mav.addObject("ticket", ticketService.getById(idTicket));
		return mav;
	}
	
	@PostMapping("/asignarEmpleado")
	 public ModelAndView vistaAsignarEmpleado(@RequestParam int idTicket, @RequestParam int idEmpleado) {
	    try {
		ticketService.cambiarEmpleado(idTicket, idEmpleado);
		return new ModelAndView("redirect:/listaTickets");
	    } catch (EmpleadoConMuchosTicketsException e) {
	        return new ModelAndView("error/empleadoConMuchosTickets");
	    }
		
	}
	
	@GetMapping("/editarTicket")
	public ModelAndView VistaEditarTicket(@RequestParam int idTicket) {
		ModelAndView mav = new ModelAndView("admin/editarTicket");
		mav.addObject("estados", estadoService.getAll());
		mav.addObject("tipoDeTickets", tipodeticketservice.getAll());
		mav.addObject("prioridades", prioridadService.getAll());
		mav.addObject("idTicket", idTicket);
		mav.addObject("ticket", ticketService.getById(idTicket));
		return mav;
	}
	
	@PostMapping("/editarTicket")
	public ModelAndView VistaEditarTicket(@RequestParam int idTicket, @RequestParam int idTipoDeTicket, @RequestParam int idPrioridad, @RequestParam int idEstado) {
	    
	    ticketService.modificarTicket(idTicket, idTipoDeTicket, idPrioridad, idEstado);
	    
		return new ModelAndView("redirect:/listaTickets");
	}
	
	@GetMapping ("/altaEmpleado")
	public ModelAndView VistaCrearEmpleado (@RequestParam(required = false) String mensaje) {
		ModelAndView mav = new ModelAndView("admin/altaEmpleado");
		mav.addObject("areas", areaService.traerTodas());
		mav.addObject("error", mensaje);
		return mav;
	}
	
	@PostMapping ("/altaEmpleado")
	public ModelAndView VistaCrearEmpleado (@RequestParam String nombre, @RequestParam String apellido,
			@RequestParam long dni, @RequestParam String rol, @RequestParam int legajo, 
			@RequestParam int idArea, @RequestParam String telefono, @RequestParam String correo,
			@RequestParam String contrasenia) {
		
		if(personaService.existsByDni(dni)) {
			throw new DniExistente("El dni ingresado ya existe.");
		}
		Empleado empleado = empleadoService.crearEmpleado(nombre, apellido, dni, legajo, idArea, rol);

	    // Verificar y crear el Contacto
		if(loginService.existsByCorreo(correo)) {
			throw new MailExistente("El correo ingresado ya esta en uso.");
		}
		Contacto contacto = contactoService.crearContacto(telefono, contrasenia, empleado);
		Login login = loginService.crearLogin(correo, contrasenia, empleado);
		
		return new ModelAndView("redirect:/listaUsuarios");
	}

	@GetMapping("/listaUsuarios")
	public ModelAndView vistaListaUsuarios(@RequestParam(required = false) String mensaje) {
		ModelAndView mav = new ModelAndView("admin/listaUsuarios");
	    mav.addObject("empleados", empleadoService.getAll());
	    mav.addObject("clientes", clienteService.getAll());
		return mav;
	}
	
	@GetMapping("/editarEmpleado")
	public ModelAndView VistaEditarEmpleado(@RequestParam int idPersona, @RequestParam(required = false) String mensaje) {
		ModelAndView mav = new ModelAndView("admin/editarEmpleado");
		Empleado empleado = empleadoService.traerPorId(idPersona);
		mav.addObject("empleado", empleado);
		mav.addObject("areas", areaService.traerTodas());
		mav.addObject("error", mensaje);
		return mav;
	}
	
	@PostMapping("/editarEmpleado")
	public ModelAndView VistaEditarEmpleado(@RequestParam int idPersona, @RequestParam String nombre, @RequestParam String apellido,
			@RequestParam long dni, @RequestParam String rol, @RequestParam int legajo, 
			@RequestParam int idArea) {
	    
	    empleadoService.modificarEmpleado(idPersona, nombre, apellido, dni, legajo, idArea, rol);
	    
		return new ModelAndView("redirect:/listaUsuarios");
	}
	
	@PostMapping("/eliminarEmpleado")
	public ModelAndView eliminarEmpleado(@RequestParam int idPersona) {
		
		ModelAndView mav = new ModelAndView("admin/listaUsuarios");
		
		empleadoService.delete(idPersona);
		
	    mav.addObject("empleados", empleadoService.getAll());
	    mav.addObject("clientes", clienteService.getAll());
		
	    return mav;
	}
	
}
