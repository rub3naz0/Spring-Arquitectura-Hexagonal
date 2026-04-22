package com.example.hexagonal.application.ports.in;

import com.example.hexagonal.domain.Task;
import java.util.List;

/**
 * Puerto de entrada (Input Port) - Define los casos de uso
 * 
 * Principios SOLID:
 * - I: Interface específica para Task (no genérica)
 * - D: El dominio y adaptadores dependen de esto, no al revés
 */
public interface TaskInputPort {
    Task createTask(String title, String description);
    Task getTaskById(int id);
    Task updateTask(int id, String title, String description);
    List<Task> getAllTasks();
    void deleteTask(Task task);
}
