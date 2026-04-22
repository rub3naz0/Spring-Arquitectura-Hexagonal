package com.example.hexagonal.adapters.in;

import com.example.hexagonal.application.ports.in.TaskInputPort;
import com.example.hexagonal.domain.Task;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TaskApi - Adaptador de entrada (REST)
 * 
 * Expone TaskInputPort como endpoints HTTP
 * Depende de TaskInputPort (abstracción), no de TaskUseCase (implementación)
 * 
 * Principios SOLID:
 * - S: Solo gestiona la conversión REST <-> Casos de uso
 * - D: Depende de TaskInputPort, no de TaskUseCase
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskApi {

    private final TaskInputPort taskInputPort;

    public TaskApi(TaskInputPort taskInputPort) {
        this.taskInputPort = taskInputPort;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskInputPort.createTask(task.getTitle(), task.getDescription());
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTaskById(@RequestParam int id) {
        Task task = taskInputPort.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> updateTask(@RequestParam int id, @RequestBody Task task) {
        Task updatedTask = taskInputPort.updateTask(id, task.getTitle(), task.getDescription());
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskInputPort.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteTask(@RequestParam int id) {
        Task task = taskInputPort.getTaskById(id);
        if (task != null) {
            taskInputPort.deleteTask(task);
            return ResponseEntity.ok("Borrado Correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Server is healthy");
    }
}
