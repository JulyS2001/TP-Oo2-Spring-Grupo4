package com.oo2.grupo4.controllers.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oo2.grupo4.dto.EmpleadoDTO;
import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.services.implementation.EmpleadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdminRestController {

    private final EmpleadoService empleadoService;

    @Operation(summary = "Obtener todos los empleados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida correctamente")
    })
    
    @GetMapping("/api/empleados")
    public List<EmpleadoDTO> getAllEmpleados() {
        return empleadoService.getAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private EmpleadoDTO convertToDTO(Empleado empleado) {
        return new EmpleadoDTO(
            empleado.getDni(),
            empleado.getNombre(),
            empleado.getApellido(),
            empleado.getRol(),
            empleado.getLegajo(),
            empleado.getArea() != null ? empleado.getArea().getNombre() : null
        );
    }
}
