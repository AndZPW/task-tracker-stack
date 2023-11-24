package dev.andzwp.taskservice.service;

import dev.andzwp.taskservice.dto.TaskRequest;
import dev.andzwp.taskservice.dto.TaskResponse;
import dev.andzwp.taskservice.exception.NoSuchTaskException;
import dev.andzwp.taskservice.mapper.TaskMapper;
import dev.andzwp.taskservice.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper<TaskResponse> taskResponseMapper;

    private final TaskMapper<TaskRequest> taskRequestMapper;

    @Override
    public TaskResponse fetchTaskById(Long id) throws NoSuchTaskException {
        var taskResponse = taskRepository.findById(id)
                .map(taskResponseMapper::mapEntityToDTO)
                .orElseThrow(() -> new NoSuchTaskException("No task present with the following id=" + id));

        log.info("The task with id={} successfully fetched", id);

        return taskResponse;
    }

    @Override
    public List<TaskResponse> fetchAll() {
        var tasks = taskRepository.findAll().stream()
                .map(taskResponseMapper::mapEntityToDTO)
                .toList();

        log.info("All tasks successfully fetched");

        return tasks;
    }

    @Override
    public List<TaskResponse> fetchAllByUserId(Long id) {
        var tasksWithUserId = taskRepository.findAllByUserId(id).stream()
                .map(taskResponseMapper::mapEntityToDTO)
                .toList();

        log.info("All tasks with userId={} successfully fetched", id);

        return tasksWithUserId;
    }


}
