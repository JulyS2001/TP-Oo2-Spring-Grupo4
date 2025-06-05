package com.oo2.grupo4.services.interfaces;

import com.oo2.grupo4.entities.Empleado;
import com.oo2.grupo4.entities.Persona;
import java.util.List;

public interface IEmpleadoService {
	
    Empleado crearEmpleado(String nombre, String apellido, int dni, long legajo, int idArea, String rol);
    
    void completarEmpleado(int idPersona, long legajo, int idArea, String rol);
    
    List<Empleado> obtenerEmpleadosPorArea(int idArea);
    
    Empleado traerPorId(int idPersona);
}