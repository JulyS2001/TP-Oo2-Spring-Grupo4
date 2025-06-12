package com.oo2.grupo4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oo2.grupo4.entities.Login;
import com.oo2.grupo4.services.implementation.LoginService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class LoginController {
	/*
    private final LoginService loginService;

    @GetMapping("/login")
    public ModelAndView vistaLogin(String mensaje) {
        ModelAndView modelAndView = new ModelAndView("Vista-login");
        modelAndView.addObject("error", mensaje);

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView procesarLogin(String correo, String contrasenia, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        try {
            Login login = loginService.login(correo, contrasenia);
            session.setAttribute("usuarioLogueado", login);

            int idPersona = login.getPersona().getIdPersona();
            mav.setViewName("redirect:/inicio?idPersona=" + idPersona);

        } catch (Exception e) {
            mav.addObject("error", e.getMessage());
            mav.setViewName("Vista-login");
        }
        return mav;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
*/
}

