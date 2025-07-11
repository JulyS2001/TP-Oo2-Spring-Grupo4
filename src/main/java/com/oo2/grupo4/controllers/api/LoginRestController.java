package com.oo2.grupo4.controllers.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oo2.grupo4.dto.LoginCreateDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginRestController {

	private final AuthenticationManager manager;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(
	    @RequestBody LoginCreateDTO dto,
	    HttpServletRequest request) {

	    try {
	        Authentication auth = manager.authenticate(
	            new UsernamePasswordAuthenticationToken(dto.correo(), dto.contrasenia())
	        );

	        SecurityContextHolder.getContext().setAuthentication(auth);

	        HttpSession session = request.getSession(true);

	        session.setAttribute(
	            HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
	            SecurityContextHolder.getContext()
	        );

	        Map<String, Object> result = new HashMap<>();
	        result.put("username", auth.getName());
	        result.put("roles", auth.getAuthorities());

	        return ResponseEntity.ok(result);

	    } catch (BadCredentialsException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body(Map.of("error", "Credenciales inválidas"));
	    }
	}
	
}
