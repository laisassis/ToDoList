package com.todolist1.toDoList.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist1.toDoList.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
	
	public Optional<Usuario> findByUsuario(String usuario);

}
