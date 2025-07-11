package com.oo2.grupo4.services.interfaces;

import com.oo2.grupo4.dto.TicketResponseDTO;
import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Cliente;
import com.oo2.grupo4.entities.Ticket;
import com.oo2.grupo4.exceptions.TicketSinActualizaciones;

import java.util.*;

public interface IClienteService {

	Cliente crearCliente(String nombre, String apellido, Long dni, String nroCliente);

	void completarCliente(int idPersona, String nroCliente);

	Cliente traerPorId(int idPersona);
	
	public List<Cliente> getAll();
	
	public List<TicketResponseDTO> getAllByClienteId(int idCliente);


}
