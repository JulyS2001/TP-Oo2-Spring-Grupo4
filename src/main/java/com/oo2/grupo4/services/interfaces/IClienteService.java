package com.oo2.grupo4.services.interfaces;

import com.oo2.grupo4.entities.Cliente;

public interface IClienteService {

    Cliente crearCliente(String nombre, String apellido, int dni, String nroCliente);
    
    void completarCliente(int idPersona, String nroCliente);
    
    Cliente traerPorId(int idPersona);
}
