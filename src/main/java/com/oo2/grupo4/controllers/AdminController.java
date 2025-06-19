package com.oo2.grupo4.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.entities.Ticket;
import com.oo2.grupo4.services.implementation.ActualizacionService;
import com.oo2.grupo4.services.implementation.EmpleadoService;
import com.oo2.grupo4.services.implementation.EstadoService;
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
	private final PersonaService personaService;
	private final TicketService ticketService;
	private final TipoDeTicketService tipodeticketservice;
	private final EstadoService estadoService;
	private final PrioridadService prioridadService;
	
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
		
		ticketService.cambiarEmpleado(idTicket, idEmpleado);
		
		return new ModelAndView("redirect:/listaTickets");
		
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
	
	
	
	
	
	
}
