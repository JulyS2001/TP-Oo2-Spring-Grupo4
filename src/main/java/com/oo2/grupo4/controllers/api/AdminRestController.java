package com.oo2.grupo4.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.oo2.grupo4.dto.EmpleadoCreateDTO;
import com.oo2.grupo4.dto.EmpleadoUpdateDTO;
import com.oo2.grupo4.mapper.IEmpleadoMapper;
import com.oo2.grupo4.dto.EmpleadoDTO;
import com.oo2.grupo4.services.implementation.EmpleadoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/empleados")
@Tag(name = "Administración", description = "Endpoints para gestión de empleados")
public class AdminRestController {

    private final EmpleadoService empleadoService;
    private final IEmpleadoMapper empleadoMapper;

 
    @Operation(summary = "Obtener todos los empleados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida correctamente")
    })
    @GetMapping
    public List<EmpleadoDTO> getAllEmpleados() {
        return empleadoService.getAllDTOs();
    }

  
    @Operation(summary = "Crear un nuevo empleado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Empleado creado exitosamente")
    })
    @PostMapping
    public EmpleadoDTO crearEmpleado(@Valid @RequestBody EmpleadoCreateDTO dto) {
    	return empleadoMapper.toDTO(empleadoService.crearEmpleado(dto));
    }

   
    @Operation(summary = "Actualizar un empleado existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empleado actualizado exitosamente")
    })
    @PostMapping("/actualizar")
    public EmpleadoDTO actualizarEmpleado(@RequestBody EmpleadoUpdateDTO dto) {
    	return empleadoMapper.toDTO(empleadoService.actualizarEmpleado(dto));

    }
}
