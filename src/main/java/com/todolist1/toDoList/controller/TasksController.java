package com.todolist1.toDoList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist1.toDoList.model.Tasks;
import com.todolist1.toDoList.repository.TasksRepository;

@RestController
@RequestMapping("/tasks")
@CrossOrigin("*")
public class TasksController {
	
	@Autowired
	private TasksRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Tasks>>GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Tasks>GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Tasks>>GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo)); 
	}
	
	@PostMapping
	public ResponseEntity<Tasks> post (@RequestBody Tasks tasks) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tasks));	
	}
	
	@PutMapping
	public ResponseEntity<Tasks> put (@RequestBody Tasks tasks) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tasks));	
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	

}
