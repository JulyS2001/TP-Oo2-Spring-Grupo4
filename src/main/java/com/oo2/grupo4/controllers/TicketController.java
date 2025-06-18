package com.oo2.grupo4.controllers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo4.exceptions.TicketYaExistente;
import com.oo2.grupo4.entities.Ticket;

import com.oo2.grupo4.services.implementation.EmailService;
import com.oo2.grupo4.services.implementation.EstadoService;
import com.oo2.grupo4.services.implementation.TicketService;
import com.oo2.grupo4.services.implementation.TipoDeTicketService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TicketController {

	private final TipoDeTicketService tipodeticketservice;
	private final EstadoService estadoService;
	private final TicketService ticketService;
	private final EmailService emailService;

	@GetMapping("/crearTicket")
	public ModelAndView vistaCrearTicket(@RequestParam(required = false) String mensaje) {
		ModelAndView mav = new ModelAndView("tickets/crearTicket");
		mav.addObject("error", mensaje);

		mav.addObject("estados", estadoService.getAll());
		mav.addObject("tipoDeTickets", tipodeticketservice.getAll());

		return mav;
	}

	@PostMapping("/crearTicket")
	public ModelAndView vistaCrearTicket(@RequestParam String titulo, @RequestParam String descripcion,
			@RequestParam int idTipoDeTicket) {

		ModelAndView mav = new ModelAndView("tickets/crearTicket");

		if (!(ticketService.existsByTitulo(titulo))) {

			Ticket ticket = ticketService.crearTicket(titulo, descripcion, idTipoDeTicket);

			String destinatario = "axel.dp44@gmail.com";
			String asunto = "[Ticket #" + ticket.getIdTicket() + "] " + titulo;
			String cuerpo = "Ha ingresado un nuevo ticket:\n\nTipo de ticket: " + ticket.getTipoDeTicket().getTipo()
					+ "\nDescripción: " + descripcion;
			emailService.enviarConfirmacionTicket(destinatario, asunto, cuerpo);

			return new ModelAndView("redirect:mail/mailEnvio");
		} else {
			throw new TicketYaExistente("El ticket con el titulo: " + titulo + " Ya existe");
		}
	}

	@GetMapping("/listaTickets")
	public ModelAndView vistaListaTickets(@RequestParam(required = false) String mensaje) {
		ModelAndView mav = new ModelAndView("tickets/listaTickets");
		mav.addObject("tickets", ticketService.getAll());
		return mav;
	}

	@GetMapping("/mail/mailEnvio")
	public ModelAndView vistaMailEnviado() {
		return new ModelAndView("mail/mailEnvio");
	}

	@PostMapping("/eliminarTicket")
	public ModelAndView eliminarTicket(@RequestParam int idTicket) {
		Ticket ticket  = ticketService.getById(idTicket);
		ticketService.delete(idTicket);
		
		ModelAndView mav = new ModelAndView("tickets/listaTickets");
	    //mav.addObject("ticket", ticket);
	    
	    return mav;
		
	}

	
}
