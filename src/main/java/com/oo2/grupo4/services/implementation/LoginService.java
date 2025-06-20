package com.oo2.grupo4.services.implementation;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.oo2.grupo4.entities.Login;
import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.repositories.ILoginRepository;
import com.oo2.grupo4.services.interfaces.ILoginService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService implements ILoginService {

	private final ILoginRepository loginRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public boolean existsByCorreo(String correo) {
		return loginRepository.existsByCorreo(correo);
	}

	@Override
	public void save(Login login) {
		loginRepository.save(login);
	}

	@Override
	public Optional<Login> validarCredenciales(String correo) {
		return loginRepository.findByCorreo(correo);
	}

	@Override
	public Login crearLogin(String correo, String contrasenia, Persona persona) {
		
		Login login = new Login();
		login.setCorreo(correo);
		login.setContrasenia(passwordEncoder.encode(contrasenia));
		login.setPersona(persona);
		
		return loginRepository.save(login);
	}

}
