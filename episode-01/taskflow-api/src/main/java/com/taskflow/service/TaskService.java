package com.taskflow.service;

import com.taskflow.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TaskService {
    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public Task createTask(Task task) {
        task.setId(idGenerator.getAndIncrement());
        tasks.put(task.getId(), task);
        return task;
    }
    
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
    
    public Optional<Task> getTaskById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }
    
    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return getTaskById(id).map(existingTask -> {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            return existingTask;
        });
    }
    
    public boolean deleteTask(Long id) {
        return tasks.remove(id) != null;
    }
}