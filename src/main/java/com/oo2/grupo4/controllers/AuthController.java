package com.oo2.grupo4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo4.entities.Cliente;
import com.oo2.grupo4.entities.Login;
import com.oo2.grupo4.services.implementation.LoginService;
import com.oo2.grupo4.services.implementation.PersonaService;
import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
public class AuthController {

	private final PersonaService personaService;
    private final LoginService loginService;

    @GetMapping("registro")
    public ModelAndView vistaRegistro(@RequestParam(required = false) String mensaje) {
        ModelAndView mav = new ModelAndView("auth/registro");
        mav.addObject("error", mensaje);
        return mav;
    }

    @PostMapping("registro")
    public ModelAndView registrarCliente(@RequestParam String nombre,
                                         @RequestParam String apellido,
                                         @RequestParam long dni,
                                         @RequestParam String correo,
                                         @RequestParam String contrasenia,
                                         @RequestParam String numCliente) {
        if (loginService.existsByCorreo(correo)) {
            return new ModelAndView("redirect:/registro?mensaje=Email ya registrado");
        }

        Cliente personaCliente = new Cliente();
        personaCliente.setNombre(nombre);
        personaCliente.setApellido(apellido);
        personaCliente.setDni(dni);
        personaCliente.setNroCliente(numCliente);
        
        personaService.save(personaCliente);

        Login login = new Login();
        login.setCorreo(correo);
        login.setContrasenia(contrasenia);
        
        login.setPersona(personaCliente);
        loginService.save(login);

        return new ModelAndView("redirect:/login");
    }
	
}
