package com.oo2.grupo4.controllers;

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

@Controller @RequiredArgsConstructor
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
    public ModelAndView registrarCliente(@RequestParam String nombre,
                                         @RequestParam String apellido,
                                         @RequestParam long dni,
                                         @RequestParam String correo,
                                         @RequestParam String contrasenia,
                                         @RequestParam String numCliente,
                                         @RequestParam String telefono) {
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
        
        Contacto contacto = new Contacto();
        contacto.setEmail(correo);
        contacto.setTelefono(telefono);
        
        contacto.setPersona(personaCliente);
        contactoService.save(contacto);

        return new ModelAndView("redirect:/");
    }
    
	
    @GetMapping("/completar-registro")
    public ModelAndView completarContacto(@RequestParam int idPersona) {
        ModelAndView mav = new ModelAndView("registro/completarContacto");
        mav.addObject("idPersona", idPersona);
        return mav;
    }
    @PostMapping("/completar-registro")
    public ModelAndView completarRegistro(
            @RequestParam int idPersona,
            @RequestParam String telefono,
            @RequestParam String email) {

        Persona persona = personaService.traerPorId(idPersona);

        if (persona == null) {
        RedirectView redirect = new RedirectView("/registro");
        redirect.addStaticAttribute("mensaje", "Persona no encontrada");
        return new ModelAndView(redirect);
        }

        if (contactoService.existeEmail(email)) {
        RedirectView redirect = new RedirectView("/completar-registro");
        redirect.addStaticAttribute("idPersona", idPersona);
        redirect.addStaticAttribute("mensaje", "El correo ya está en uso");
        return new ModelAndView(redirect);
        }

        contactoService.crearContacto(telefono, email, persona);

        return new ModelAndView("redirect:/inicio?idPersona=" + idPersona);
    }
}
