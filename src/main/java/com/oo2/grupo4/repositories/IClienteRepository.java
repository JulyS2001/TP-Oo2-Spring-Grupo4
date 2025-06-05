package com.oo2.grupo4.repositories;


import com.oo2.grupo4.entities.Cliente;

public interface IClienteRepository {

	 Cliente findByNroCliente(String nroCliente); // Buscar por número de cliente

}
