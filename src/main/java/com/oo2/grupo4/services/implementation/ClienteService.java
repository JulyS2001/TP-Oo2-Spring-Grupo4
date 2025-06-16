package com.oo2.grupo4.services.implementation;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Cliente;
import com.oo2.grupo4.repositories.IClienteRepository;
import com.oo2.grupo4.services.interfaces.IClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService {

	private final IClienteRepository clienteRepository;
	private final PersonaService personaService;

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

}