package com.oo2.grupo4.services.implementation;

import java.util.Optional;

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

	/*
	 * @Override
	 * 
	 * @Transactional public Optional<Login> registrarLogin(String correo, String
	 * contrasenia, Persona persona) { if (loginRepository.existsByCorreo(correo)) {
	 * return Optional.empty(); } Login login = Login.builder() .correo(correo)
	 * .contrasenia(contrasenia) .persona(persona) .build();
	 * 
	 * return Optional.of(loginRepository.save(login)); }
	 * 
	 * @Override public Login login(String correo, String contrasenia) throws
	 * Exception { Login login = loginRepository.findByCorreo(correo)
	 * .orElseThrow(() -> new
	 * IllegalArgumentException("El correo no se encuentra registrado"));
	 * 
	 * if (!login.getContrasenia().equals(contrasenia)) { throw new
	 * IllegalArgumentException("La contraseña es incorrecta"); }
	 * 
	 * return login; }
	 * 
	 * @Override public Login actualizarContrasenia(int idLogin, String
	 * nuevaContrasenia) { Login login = loginRepository.findById(idLogin)
	 * .orElseThrow(() -> new IllegalArgumentException("Login no encontrado"));
	 * login.setContrasenia(nuevaContrasenia); return loginRepository.save(login); }
	 * 
	 * @Override public boolean existeCorreo(String correo) { return
	 * loginRepository.existsByCorreo(correo); }
	 */

}
