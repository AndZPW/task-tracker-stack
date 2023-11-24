package dev.andzwp.taskservice.mapper;

import dev.andzwp.taskservice.dto.TaskRequest;
import dev.andzwp.taskservice.model.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service("task-request-mapper")
@RequiredArgsConstructor
public class TaskRequestMapper implements TaskMapper<TaskRequest> {

    private final ModelMapper modelMapper;

    @Override
    public TaskRequest mapEntityToDTO(Task task) {
        return modelMapper.map(task, TaskRequest.class);
    }

    @Override
    public Task mapDTOtoEntity(TaskRequest dto) {
        return modelMapper.map(dto, Task.class);
    }
}
