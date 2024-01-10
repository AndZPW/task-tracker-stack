package dev.andzwp.taskservice.service;

import dev.andzwp.taskservice.dto.TaskDTO;
import dev.andzwp.taskservice.dto.TaskRequest;
import dev.andzwp.taskservice.dto.TaskResponse;
import dev.andzwp.taskservice.exception.NoSuchTaskException;
import dev.andzwp.taskservice.mapper.TaskMapper;
import dev.andzwp.taskservice.model.Task;
import dev.andzwp.taskservice.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {
    private final ModelMapper modelMapper;

    private final TaskRepository taskRepository;

    private final TaskMapper<TaskResponse> taskResponseMapper;

    private final TaskMapper<TaskRequest> taskRequestMapper;

    @Override
    public TaskResponse fetchTaskById(Long id) throws NoSuchTaskException {
        var task = findTaskById(id);

        log.info("The task with id={} successfully fetched", id);

        return modelMapper.map(task, TaskResponse.class);
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

    @Override
    public void deleteTaskById(Long id) throws NoSuchTaskException {
        var task = findTaskById(id);
        taskRepository.delete(task);
        log.info("Task with id={} successfully deleted", id);
    }

    @Override
    public void deleteAllTasksByUserId(Long userId) {
        var tasks = taskRepository.findAllByUserId(userId);
        taskRepository.deleteAll(tasks);
        log.info("All tasks with userId={} successfully deleted", userId);
    }

    @Override
    public void createTask(TaskRequest taskRequest) {
        var task = taskRequestMapper.mapDTOtoEntity(taskRequest);
        var id = taskRepository.save(task).getId();
        log.info("Task with id={} successfully created", id);
    }

    @Override
    public void updateTask(Long id, TaskDTO dto) throws NoSuchTaskException {
        var task = findTaskById(id);
        task.setHeader(dto.header());
        task.setContent(dto.content());
        task.setStatus(dto.status());
        taskRepository.save(task);
        log.info("Task with id={} successfully updated", id);
    }

    @Override
    public void patchTask(Long id, TaskDTO dto) throws NoSuchTaskException, IllegalAccessException {
        var model = findTaskById(id);

        var dtoClass = TaskDTO.class;
        var modelClass = Task.class;

        var fieldsDTO = dtoClass.getDeclaredFields();
        var fieldsModel = modelClass.getDeclaredFields();

        for (int i = 0; i < fieldsDTO.length; ++i) {
            fieldsDTO[i].setAccessible(true);
            fieldsModel[i + 1].setAccessible(true);

            var value = fieldsDTO[i].get(dto);
            if (value != null) fieldsModel[i + 1].set(model, value);

            fieldsDTO[i].setAccessible(false);
            fieldsModel[i + 1].setAccessible(false);
        }

        taskRepository.save(model);
    }

    private Task findTaskById(Long id) throws NoSuchTaskException {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchTaskException("No task present with the following id=" + id));
    }

}
