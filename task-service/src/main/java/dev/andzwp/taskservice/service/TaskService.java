package dev.andzwp.taskservice.service;

import dev.andzwp.taskservice.dto.TaskDTO;
import dev.andzwp.taskservice.dto.TaskRequest;
import dev.andzwp.taskservice.dto.TaskResponse;
import dev.andzwp.taskservice.exception.NoSuchTaskException;

import java.util.List;

public interface TaskService {
    TaskResponse fetchTaskById(Long id) throws NoSuchTaskException;

    List<TaskResponse> fetchAll();

    List<TaskResponse> fetchAllByUserId(Long id);

    void deleteTaskById(Long id) throws NoSuchTaskException;

    void deleteAllTasksByUserId(Long userId);

    void createTask(TaskRequest taskRequest);

    void updateTask(Long id, TaskDTO dto) throws NoSuchTaskException;

    void patchTask(Long id, TaskDTO dto) throws NoSuchTaskException, IllegalAccessException;
}
