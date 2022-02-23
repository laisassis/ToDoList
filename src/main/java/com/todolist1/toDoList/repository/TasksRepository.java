package com.todolist1.toDoList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist1.toDoList.model.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long>{
	
	public List<Tasks> findAllByTituloContainingIgnoreCase (String titulo);

}
