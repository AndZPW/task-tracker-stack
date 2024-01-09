package dev.andzwp.taskservice.controller;

import dev.andzwp.taskservice.aop.ValidRequestParam;
import dev.andzwp.taskservice.dto.ErrorResponse;
import dev.andzwp.taskservice.dto.Response;
import dev.andzwp.taskservice.dto.TaskRequest;
import dev.andzwp.taskservice.dto.TaskResponse;
import dev.andzwp.taskservice.exception.NoSuchTaskException;
import dev.andzwp.taskservice.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
@Tag(name = "Tasks", description = "Methods for task management")
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Get task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the task",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})

    })
    @ValidRequestParam(min = 1)
    @ResponseStatus(HttpStatus.OK)
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


    @DeleteMapping("/{id}")
    @ValidRequestParam(min = 1)
    public ResponseEntity<?> deleteEntityById(@PathVariable("id") Long id) throws NoSuchTaskException {
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @ValidRequestParam(min = 1)
    @DeleteMapping(params = {"userId"})
    public ResponseEntity<?> deleteAllTasksByUserId(@RequestParam("id") Long userId) {
        taskService.deleteAllTasksByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Response createTask(@Validated TaskRequest taskRequest) {
        taskService.createTask(taskRequest);
        return new Response(HttpStatus.CREATED, "Task successfully created");
    }
}
