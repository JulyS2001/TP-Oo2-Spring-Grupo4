package com.oo2.grupo4.controllers.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo4.dto.ActualizacionCreateDTO;
import com.oo2.grupo4.dto.ActualizacionResponseDTO;
import com.oo2.grupo4.security.UserDetailsImpl;
import com.oo2.grupo4.services.implementation.ActualizacionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ActualizacionRestController {

	private final ActualizacionService actualizacionService;

	@PostMapping("/crearActualizacionRest/{idTicket}")
	public ResponseEntity<ActualizacionResponseDTO> crearActualizacion(@PathVariable int idTicket,
			@RequestBody ActualizacionCreateDTO dto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

		int idPersona = userDetails.getLogin().getPersona().getIdPersona();

		ActualizacionCreateDTO dtoNuevo = new ActualizacionCreateDTO(dto.contenido(), idPersona, idTicket);

		ActualizacionResponseDTO creada = actualizacionService.crearActualizacion(dtoNuevo);

		return ResponseEntity.status(HttpStatus.CREATED).body(creada);
	}

	@GetMapping("/listaActualizacionesRest/{idTicket}")
	public ResponseEntity<List<ActualizacionResponseDTO>> listaActualizaciones(@PathVariable int idTicket){
		List<ActualizacionResponseDTO> actualizaciones = actualizacionService.listaActualizacionesDTOporTicket(idTicket);
		
		return ResponseEntity.ok(actualizaciones);
	}
	
	@DeleteMapping("/eliminarActualizacionRest/{idTicket}/{idActualizacion}")
	public ResponseEntity<Void> eliminarActualizacion(@PathVariable int idTicket, @PathVariable int idActualizacion){
		
		actualizacionService.delete(idTicket, idActualizacion);
		
		return ResponseEntity.noContent().build();
	}
}
