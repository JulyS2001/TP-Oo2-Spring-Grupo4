package com.oo2.grupo4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.services.implementation.ContactoService;
import com.oo2.grupo4.services.implementation.LoginService;
import com.oo2.grupo4.services.implementation.PersonaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class RegistroController {
/*
    private final ContactoService contactoService;
    private final LoginService loginService;
    private final PersonaService personaService;

    @GetMapping("/completar-registro")
    public ModelAndView completarRegistro(
            @RequestParam int idPersona,
            @RequestParam(required = false) String mensaje) {

        ModelAndView mav = new ModelAndView("Vista-completarRegistro");

        Persona persona = personaService.traerPorId(idPersona);
        if (persona == null) {
            mav.setViewName("redirect:/registro?mensaje=Usuario no encontrado");
            return mav;
        }

        mav.addObject("idPersona", idPersona);
        mav.addObject("mensaje", mensaje);
        return mav;
    }

    @PostMapping("/completar-registro")
    public ModelAndView completarRegistro(
            @RequestParam int idPersona,
            @RequestParam String telefono,
            @RequestParam String email,
            @RequestParam String contrasenia) {

        Persona persona = personaService.traerPorId(idPersona);

        if (persona == null) {
        RedirectView redirect = new RedirectView("/registro");
        redirect.addStaticAttribute("mensaje", "Persona no encontrada");
        return new ModelAndView(redirect);
        }

        if (contactoService.existeEmail(email) || loginService.existeCorreo(email)) {
        RedirectView redirect = new RedirectView("/completar-registro");
        redirect.addStaticAttribute("idPersona", idPersona);
        redirect.addStaticAttribute("mensaje", "El correo ya está en uso");
        return new ModelAndView(redirect);
        }

        contactoService.crearContacto(telefono, email, persona);
        loginService.registrarLogin(email, contrasenia, persona);

        return new ModelAndView("redirect:/inicio?idPersona=" + idPersona);
    }*/
}
