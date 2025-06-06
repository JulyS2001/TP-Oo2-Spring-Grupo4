package com.oo2.grupo4.services.implementation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Contacto;
import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.repositories.IContactoRepository;
import com.oo2.grupo4.services.interfaces.IClienteService;
import com.oo2.grupo4.services.interfaces.IContactoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactoService implements IContactoService {

	private final IContactoRepository contactoRepository;

    @Override
    @Transactional
    public Optional<Contacto> crearContacto(String telefono, String email, Persona persona) {
        if (contactoRepository.existsByEmail(email)) {
            return Optional.empty();
        }

        Contacto contacto = Contacto.builder()
                .telefono(telefono)
                .email(email)
                .persona(persona)
                .build();

        return Optional.of(contactoRepository.save(contacto));
    }

    @Override
    public Contacto traerPorId(int idContacto) {
        return contactoRepository.findById(idContacto)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + idContacto));
    }

    @Override
    public int actualizarContacto(int idContacto, String telefono, String email) {
        Contacto contacto = this.traerPorId(idContacto);

        contacto.setTelefono(telefono);
        contacto.setEmail(email);

        return contactoRepository.save(contacto).getIdContacto();
    }

    @Override
    public boolean existeEmail(String email) {
        return contactoRepository.existsByEmail(email);
    }

    @Override
    public void eliminarContacto(int idContacto) {
        contactoRepository.deleteById(idContacto);
    }
}
