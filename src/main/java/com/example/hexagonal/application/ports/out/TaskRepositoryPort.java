package com.example.hexagonal.application.ports.out;

import com.example.hexagonal.domain.Task;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida (Output Port) - Define la persistencia
 * 
 * Principios SOLID:
 * - I: Interface específica para Task (no genérica)
 * - D: TaskUseCase depende de esto (abstracción), no de implementación
 * 
 * El adaptador (TaskRepositoryAdapter) implementará esto usando TaskRepository (JPA)
 */
public interface TaskRepositoryPort {
    Task save(Task task);
    Optional<Task> findById(int id);
    List<Task> findAll();
    void delete(Task task);
}
