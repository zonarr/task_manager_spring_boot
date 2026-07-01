package com.sun.task_manager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/task")
public class TaskManagerController {
    private final TaskManagerRepository taskManagerRepository;

    TaskManagerController(TaskManagerRepository taskManagerRepository) {
        this.taskManagerRepository = taskManagerRepository;
    }

    @PostMapping
    public ResponseEntity<Void> postMethodName(@RequestBody Task task,UriComponentsBuilder uriBuilder) {
        Task newTask = taskManagerRepository.save(task);
        URI uri = uriBuilder.path("/task/{id}").buildAndExpand(newTask.id()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable int id) {
        Optional<Task> task = taskManagerRepository.findById(id);
        if (task.isPresent()){
            return ResponseEntity.ok(task.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
