package dev.andzwp.taskservice.controller;

import dev.andzwp.taskservice.aop.ValidRequestParam;
import dev.andzwp.taskservice.dto.*;
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

    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found tasks",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),

    })
    @ResponseStatus(HttpStatus.OK)
    @ValidRequestParam(min = 1)
    @GetMapping(params = {"userId"})
    public List<TaskResponse> fetchAllTasks(@RequestParam(name = "userId", required = false) Long id) {
        if (id == null) return taskService.fetchAll();
        else return taskService.fetchAllByUserId(id);
    }


    @Operation(summary = "Delete task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete the task",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema())}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})

    })
    @DeleteMapping("/{id}")
    @ValidRequestParam(min = 1)
    public ResponseEntity<?> deleteEntityById(@PathVariable("id") Long id) throws NoSuchTaskException {
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete all tasks associated with userId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete all tasks",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema())}),
            @ApiResponse(responseCode = "400", description = "Invalid userId supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),

    })
    @ValidRequestParam(min = 1)
    @DeleteMapping(params = {"userId"})
    public ResponseEntity<?> deleteAllTasksByUserId(@RequestParam("id") Long userId) {
        taskService.deleteAllTasksByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Create the task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create the task",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),


    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Response createTask(@Validated TaskRequest taskRequest) {
        taskService.createTask(taskRequest);
        return new Response(HttpStatus.CREATED, "Task successfully created");
    }

    @Operation(summary = "Update task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update the task",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema())}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})

    })
    @PutMapping("/{id}")
    @ValidRequestParam(min = 1)
    public ResponseEntity<?> updateTask(@RequestBody @Validated TaskDTO dto, @PathVariable Long id) throws NoSuchTaskException {
        taskService.updateTask(id, dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Patch task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Patch the task",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema())}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})

    })
    @PatchMapping("/{id}")
    @ValidRequestParam(min = 1)
    public ResponseEntity<?> patchTask(@RequestBody TaskDTO dto, @PathVariable Long id) throws NoSuchTaskException, IllegalAccessException {
        taskService.patchTask(id, dto);
        return ResponseEntity.noContent().build();
    }
}
