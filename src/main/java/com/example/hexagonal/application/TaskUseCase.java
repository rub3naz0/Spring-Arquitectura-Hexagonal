package com.example.hexagonal.application;

import com.example.hexagonal.application.ports.in.TaskInputPort;
import com.example.hexagonal.application.ports.out.TaskRepositoryPort;
import com.example.hexagonal.domain.Task;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TaskUseCase - Caso de uso que implementa la lógica de negocio
 * 
 * Implementa TaskInputPort (lo que puede hacer con Tasks)
 * Depende de TaskRepositoryPort (qué necesita de persistencia)
 * 
 * Principios SOLID:
 * - S: Solo gestiona la lógica de negocio de Task
 * - O: Puedo crear otros casos de uso sin modificar este
 * - D: Depende de TaskRepositoryPort (abstracción), no de TaskRepositoryAdapter
 */
@Service
public class TaskUseCase implements TaskInputPort {

    private final TaskRepositoryPort taskRepositoryPort;

    public TaskUseCase(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Task createTask(String title, String description) {
        Task task = new Task(title, description);
        return taskRepositoryPort.save(task);
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepositoryPort.findById(id).orElse(null);
    }

    @Override
    public Task updateTask(int id, String title, String description) {
        return taskRepositoryPort.findById(id)
            .map(task -> {
                task.setTitle(title);
                task.setDescription(description);
                return taskRepositoryPort.save(task);
            })
            .orElse(null);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepositoryPort.findAll();
    }

    @Override
    public void deleteTask(Task task) {
        taskRepositoryPort.delete(task);
    }
}