package com.oo2.grupo4.services.implementation;

import org.springframework.stereotype.Service;
import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.repositories.IPersonaRepository;
import com.oo2.grupo4.services.interfaces.IPersonaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService implements IPersonaService{


    private final IPersonaRepository personaRepository;

    @Override
    public Persona traerPorId(int idPersona) {
        return personaRepository.findById(idPersona)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + idPersona));
    }

    @Override
    public Persona traerPorDni(int dni) {
        return personaRepository.findByDni(dni)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con DNI: " + dni));
    }

    @Override
    public int actualizarPersona(int idPersona, String nombre, String apellido, int dni) {
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
    public void validarDniNoExiste(int dni) {
        if (personaRepository.existsByDni(dni)) {
            throw new IllegalArgumentException("Ya existe una persona con ese DNI");
        }
    }
}
