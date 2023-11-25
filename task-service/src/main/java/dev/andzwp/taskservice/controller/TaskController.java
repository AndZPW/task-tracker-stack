package dev.andzwp.taskservice.controller;

import dev.andzwp.taskservice.aop.ValidRequestParam;
import dev.andzwp.taskservice.dto.Response;
import dev.andzwp.taskservice.dto.TaskRequest;
import dev.andzwp.taskservice.dto.TaskResponse;
import dev.andzwp.taskservice.exception.NoSuchTaskException;
import dev.andzwp.taskservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @ResponseStatus(HttpStatus.OK)
    @ValidRequestParam(min = 1)
    @GetMapping("/{id}")
    public TaskResponse fetchTaskById(@PathVariable("id") Long id) throws NoSuchTaskException {
        return taskService.fetchTaskById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ValidRequestParam(min = 1)
    @GetMapping(params = {"userId"})
    public List<TaskResponse> fetchAllTasks(@RequestParam(name = "userId", required = false) Long id) {
        if (id == null) return taskService.fetchAll();
        else return taskService.fetchAllByUserId(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @ValidRequestParam(min = 1)
    public Response deleteEntityById(@PathVariable("id") Long id) throws NoSuchTaskException {
        taskService.deleteTaskById(id);
        return new Response(HttpStatus.NO_CONTENT, "Task with id=" + id + " successfully deleted");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ValidRequestParam(min = 1)
    @DeleteMapping(params = {"userId"})
    public Response deleteAllTasksByUserId(@RequestParam("id") Long userId) {
        taskService.deleteAllTasksByUserId(userId);
        return new Response(HttpStatus.NO_CONTENT, "Tasks with userId=" + userId + " successfully deleted");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Response createTask(@Validated TaskRequest taskRequest){
        taskService.createTask(taskRequest);
        return new Response(HttpStatus.CREATED,"Task successfully created");
    }
}
