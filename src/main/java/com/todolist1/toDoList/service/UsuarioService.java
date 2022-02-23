package com.todolist1.toDoList.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.todolist1.toDoList.model.UserLogin;
import com.todolist1.toDoList.model.Usuario;
import com.todolist1.toDoList.repository.UsuarioRepository;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return repository.save(usuario);
	}
	
	
	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getEmail());
		
		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
			
			String auth = user.get().getEmail() + ":" + user.get().getSenha();
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
			String authHeader = "Basic " + new String(encodedAuth);

			user.get().setNome(usuario.get().getNome());
			user.get().setToken(authHeader);

			return user;
			}
		}
		
		throw new ResponseStatusException(
				HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
		
	
	
	
	}

}


