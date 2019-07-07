package com.johnson.toDoList;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TodoController {
	
	@Autowired
	private TodoTaskRepository todoTaskRepository; 
	
	
	
	@GetMapping(value="/")
	public String index(TodoTask todoTask, Model model) {
		model.addAttribute("tasks", todoTaskRepository.findAll());
		return "todolist/index";
	}
	
	@GetMapping(value = "/todolist/new")
	public String newTask(TodoTask todoTask) {
		return "todolist/new";
	}
	
	
	// post new task to database
		@PostMapping("todolist/new")
		public String submitTodoTask(TodoTask todoTask, Model model) {
			model.addAttribute("id", todoTask.getId());
			model.addAttribute("title", todoTask.getTitle());
			model.addAttribute("createdBy", todoTask.getCreatedBy());
			model.addAttribute("description", todoTask.getDescription());
			model.addAttribute("status", todoTask.isStatus());
			
			todoTaskRepository.save(todoTask);
			model.addAttribute("todoTasks", todoTaskRepository.findAll());
			return "todolist/result";
		}
	
//		to delete a task using id
		
//		@GetMapping(value = "/todolist/result/{id}")
//		public String showTaskWithId(@PathVariable Long id, TodoTask todoTask, Model model) {
//			model.addAttribute("todoTasks", todoTaskRepository.findAll());
//			return "todolist/show";
//		}
			
	@DeleteMapping(value = "/todolist/result/{id}")
    public String deleteTaskWithId(@PathVariable Long id, TodoTask todoTask, Model model) {
        todoTaskRepository.deleteById(id);
        model.addAttribute("todoTasks", 
        		todoTaskRepository.findAll());
        return "todolist/result";

    }
	
	@GetMapping("todolist/update/{id}")
	public String getUpdatePage(@PathVariable Long id, TodoTask todoTask, Model model) {
		TodoTask task = todoTaskRepository.findById(id).orElseThrow(() -> (new IllegalArgumentException("invalid id " + id)));
		model.addAttribute("tasks", task);
		return "todolist/update";
	}

	// EDIT a specific task on edit page
	@PutMapping("todolist/update/{id}")
	public String updateTask(@PathVariable Long id, TodoTask todoTask, Model model) {
		TodoTask editTask = todoTaskRepository.findById(id).orElse(null);
		todoTask.setTitle(todoTask.getTitle());
		todoTask.setCreatedBy(todoTask.getCreatedBy());
		todoTask.setDescription(todoTask.getDescription());
		todoTask.setStatus(todoTask.isStatus());
		
		todoTaskRepository.save(todoTask);
		model.addAttribute("tasks", todoTaskRepository.findAll());
		return "todolist/result";
	}
	
}