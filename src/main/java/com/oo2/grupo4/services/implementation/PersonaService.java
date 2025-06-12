package com.oo2.grupo4.services.implementation;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Cliente;
import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.repositories.IClienteRepository;
import com.oo2.grupo4.repositories.IPersonaRepository;
import com.oo2.grupo4.services.interfaces.IPersonaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService implements IPersonaService{


    private final IPersonaRepository personaRepository;
    private final IClienteRepository clienteRepository;
    
    @Override
    public Persona save(Persona persona) {
    	if (persona instanceof Cliente) {
            return clienteRepository.save((Cliente) persona);
        }
        throw new IllegalArgumentException("Tipo de persona no soportado.");
    }

    @Override
    public Persona traerPorId(int idPersona) {
        return personaRepository.findById(idPersona)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + idPersona));
    }

    @Override
    public Persona traerPorDni(Long dni) {
        return personaRepository.findByDni(dni)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con DNI: " + dni));
    }

    @Override
    public int actualizarPersona(int idPersona, String nombre, String apellido, Long dni) {
        Persona persona = this.traerPorId(idPersona);

        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setDni(dni);

        return personaRepository.save(persona).getIdPersona();
    }

    @Override
    public void eliminarPersona(int idPersona) {
        personaRepository.deleteById(idPersona);
    }

    @Override
    public void validarDniNoExiste(Long dni) {
        if (personaRepository.existsByDni(dni)) {
            throw new IllegalArgumentException("Ya existe una persona con ese DNI");
        }
    }
}
