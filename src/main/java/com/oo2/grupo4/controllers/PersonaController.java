package com.oo2.grupo4.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo4.entities.Area;
import com.oo2.grupo4.entities.Cliente;
import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.services.implementation.AreaService;
import com.oo2.grupo4.services.implementation.ClienteService;
import com.oo2.grupo4.services.implementation.EmpleadoService;
import com.oo2.grupo4.services.implementation.PersonaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;
    private final ClienteService clienteService;
    private final EmpleadoService empleadoService;
    private final AreaService areaService;
    
    

    @GetMapping("/registro")
    public ModelAndView vistaRegistro(@RequestParam(required = false) String mensaje) {
        ModelAndView mav = new ModelAndView("Vista-registro");
        mav.addObject("error", mensaje);
        return mav;
    }

    @PostMapping("/registro")
    public ModelAndView registrar(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam Long dni,
            @RequestParam String tipoUsuario) {

        try {
            personaService.validarDniNoExiste(dni);
        } catch (RuntimeException e) {
            return new ModelAndView("redirect:/registro?mensaje=El DNI ya se encuentra registrado");
        }

        if ("cliente".equalsIgnoreCase(tipoUsuario)) {
            Cliente cliente = clienteService.crearCliente(nombre, apellido, dni, null);
            return new ModelAndView("redirect:/registro-cliente?idPersona=" + cliente.getIdPersona());
        } else if ("empleado".equalsIgnoreCase(tipoUsuario)) {
            Empleado empleado = empleadoService.crearEmpleado(nombre, apellido, dni, null, 0, null);
            return new ModelAndView("redirect:/registro-empleado?idPersona=" + empleado.getIdPersona());
        } else {
            return new ModelAndView("redirect:/registro?mensaje=Tipo de usuario inválido");
        }
    }

    @GetMapping("/registro-cliente")
    public ModelAndView vistaRegistroCliente(
            @RequestParam int idPersona,
            @RequestParam(required = false) String mensaje) {

        Cliente cliente = clienteService.traerPorId(idPersona);

        ModelAndView mav = new ModelAndView("Vista-registroCliente");
        mav.addObject("idPersona", cliente.getIdPersona());
        mav.addObject("nombre", cliente.getNombre());
        mav.addObject("apellido", cliente.getApellido());
        mav.addObject("dni", cliente.getDni());
        mav.addObject("mensaje", mensaje);
        return mav;
    }

    @PostMapping("/registro-cliente")
    public ModelAndView crearCliente(
            @RequestParam int idPersona,
            @RequestParam String nroCliente) {

        try {
            clienteService.completarCliente(idPersona, nroCliente);
            return new ModelAndView("redirect:/completar-registro?idPersona=" + idPersona);
        } catch (Exception e) {
            RedirectView redirect = new RedirectView("/registro-cliente");
            redirect.addStaticAttribute("idPersona", idPersona);
            redirect.addStaticAttribute("mensaje", "Error al crear cliente");
            return new ModelAndView(redirect);
        }
    }

    @GetMapping("/registro-empleado")
    public ModelAndView vistaRegistroEmpleado(
            @RequestParam int idPersona,
            @RequestParam(required = false) String mensaje) {

        Empleado empleado = empleadoService.traerPorId(idPersona);
        List<Area> areas = areaService.traerTodas();

        ModelAndView mav = new ModelAndView("Vista-registroEmpleado");
        mav.addObject("idPersona", empleado.getIdPersona());
        mav.addObject("nombre", empleado.getNombre());
        mav.addObject("apellido", empleado.getApellido());
        mav.addObject("dni", empleado.getDni());
        mav.addObject("areas", areas);
        mav.addObject("mensaje", mensaje);
        return mav;
    }

    @PostMapping("/registro-empleado")
    public ModelAndView registrarEmpleado(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam Long dni,
            @RequestParam Integer legajo,
            @RequestParam String rol,
            @RequestParam int idArea) {

        try {
            Empleado empleado = empleadoService.crearEmpleado(nombre, apellido, dni, legajo, idArea, rol);
            return new ModelAndView("redirect:/completar-registro");
        } catch (Exception e) {
            RedirectView redirect = new RedirectView("/registro-empleado");
            redirect.addStaticAttribute("nombre", nombre);
            redirect.addStaticAttribute("apellido", apellido);
            redirect.addStaticAttribute("dni", dni);
            redirect.addStaticAttribute("mensaje", "Error al crear empleado");
            return new ModelAndView(redirect);
        }
    }

    @GetMapping("/baja/{id}")

    public RedirectView eliminar(@PathVariable("id") int idPersona) {
        personaService.eliminarPersona(idPersona); // cascada borra login y contacto
        return new RedirectView("/");
    }
}


