package com.oo2.grupo4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oo2.grupo4.entities.TipoDeTicket;

import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
@RequestMapping("/")
public class TicketController {
	
	private final TipoDeTicketService tipodeticket;
	
	

}
