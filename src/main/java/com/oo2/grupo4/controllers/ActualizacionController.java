package com.oo2.grupo4.controllers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.entities.Ticket;
import com.oo2.grupo4.services.implementation.ActualizacionService;
import com.oo2.grupo4.services.implementation.EmpleadoService;
import com.oo2.grupo4.services.implementation.PersonaService;
import com.oo2.grupo4.services.implementation.TicketService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ActualizacionController {

	private final ActualizacionService actualizacionService;
	private final EmpleadoService empleadoService;
	private final PersonaService personaService;
	private final TicketService ticketService;

	@GetMapping("/crearActualizacion")
	public ModelAndView crearActualizacionVista(@RequestParam int idTicket) {
		ModelAndView mav = new ModelAndView("actualizacion/crearActualizacion");
		mav.addObject("empleados", empleadoService.getAll());
		mav.addObject("idTicket", idTicket);
		return mav;
	}

	@PostMapping("/crearActualizacion")
	 public ModelAndView crearActualizacion(@RequestParam int idTicket, @RequestParam String contenido) {
		
		ModelAndView mav = new ModelAndView("actualizacion/listaActualizaciones");
		
		Ticket ticket = ticketService.getById(idTicket);
		
		Actualizacion actualizacion = new Actualizacion();
		actualizacion.setTicket(ticket);
		actualizacion.setContenido(contenido);
		actualizacion.setFechaActualizacion(LocalDateTime.now());
		
		actualizacionService.save(actualizacion);
		
		mav.addObject("actualizaciones", actualizacionService.getAllByTicketId(idTicket));
		mav.addObject("idTicket", idTicket);
		
		return mav;
		
	}

	@GetMapping("/listaActualizaciones")
	public ModelAndView listaActualizaciones(@RequestParam int idTicket) {
		ModelAndView mav = new ModelAndView("actualizacion/listaActualizaciones");
		mav.addObject("actualizaciones", actualizacionService.getAllByTicketId(idTicket));
		mav.addObject("idTicket", idTicket);
		return mav;
	}
	
	@PostMapping("/eliminarActualizacion")
	public ModelAndView eliminarActualizacion(@RequestParam int idTicket, @RequestParam int idActualizacion) {
		ModelAndView mav = new ModelAndView("actualizacion/listaActualizaciones");
		
		actualizacionService.delete(idTicket, idActualizacion);
		
		mav.addObject("actualizaciones", actualizacionService.getAllByTicketId(idTicket));
		mav.addObject("idTicket", idTicket);
		
		return mav;
	}
	
	

}




