package com.example.hexagonal.adapters.out;

import com.example.hexagonal.application.ports.out.TaskRepositoryPort;
import com.example.hexagonal.domain.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * TaskRepositoryAdapter - Adaptador de salida para persistencia
 * 
 * Implementa TaskRepositoryPort (el contrato que necesita la aplicación)
 * Usa TaskRepository (detalles de JPA - privado)
 * 
 * Principios SOLID:
 * - S: Solo gestiona la persistencia con JPA
 * - L: Puede sustituir a TaskRepositoryPort en cualquier lugar
 * - D: Implementa una abstracción (TaskRepositoryPort)
 */
@Component
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final TaskRepository taskRepository;

    public TaskRepositoryAdapter(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> findById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }
}
