package com.taskflow.web;

import com.taskflow.model.Task;
import com.taskflow.service.TaskService;
import io.helidon.http.Status;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

public class TaskResource implements HttpService {
    private final TaskService taskService;
    
    public TaskResource() {
        this.taskService = new TaskService();
    }
    
    @Override
    public void routing(HttpRules rules) {
        rules
            .get("/", this::getAllTasks)
            .get("/{id}", this::getTaskById)
            .post("/", this::createTask);
    }
    
    private void getAllTasks(ServerRequest request, ServerResponse response) {
        response.send(taskService.getAllTasks());
    }
    
    private void getTaskById(ServerRequest request, ServerResponse response) {
        long id = Long.parseLong(request.path().pathParameters().get("id"));
        
        taskService.getTaskById(id)
            .ifPresentOrElse(
                response::send,
                () -> response.status(Status.NOT_FOUND_404).send()
            );
    }
    
    private void createTask(ServerRequest request, ServerResponse response) {
        Task task = new Task();
        task.setTitle("Sample Task");
        task.setDescription("Created from API");
        
        Task created = taskService.createTask(task);
        response.status(Status.CREATED_201).send(created);
    }
}