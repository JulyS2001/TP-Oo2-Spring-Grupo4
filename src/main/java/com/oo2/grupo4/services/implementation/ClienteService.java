package com.oo2.grupo4.services.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Actualizacion;
import com.oo2.grupo4.entities.Cliente;
import com.oo2.grupo4.entities.Ticket;
import com.oo2.grupo4.repositories.IClienteRepository;
import com.oo2.grupo4.repositories.ITicketRepository;
import com.oo2.grupo4.services.interfaces.IClienteService;
import com.oo2.grupo4.dto.TicketResponseDTO;
import com.oo2.grupo4.mapper.ITicketMapper;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService {

	private final IClienteRepository clienteRepository;
	private final PersonaService personaService;
	private final ITicketRepository ticketRepository;
	private final ITicketMapper ticketMapper;


	@Override
	public Cliente crearCliente(String nombre, String apellido, Long dni, String nroCliente) {
		personaService.validarDniNoExiste(dni);

		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setDni(dni);
		cliente.setNroCliente(nroCliente);

		return clienteRepository.save(cliente);
	}

	@Override
	public void completarCliente(int idPersona, String nroCliente) {
		Cliente cliente = clienteRepository.findById(idPersona)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + idPersona));

		cliente.setNroCliente(nroCliente);
		clienteRepository.save(cliente);
	}

	@Override
	public Cliente traerPorId(int idPersona) {
		return clienteRepository.findById(idPersona)
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + idPersona));
	}
	@Override
	public List<Cliente> getAll(){
		return clienteRepository.findAll();
	}
	
	@Override
	public List<TicketResponseDTO> getAllByClienteId(int idCliente){
		return ticketRepository.findAll().stream().filter(t -> t.getCliente() != null && t.getCliente().getIdPersona() ==idCliente)
				.map(ticketMapper::toDTO).collect(Collectors.toList());
	}

}