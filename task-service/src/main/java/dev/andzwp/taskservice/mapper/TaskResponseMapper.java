package dev.andzwp.taskservice.mapper;

import dev.andzwp.taskservice.dto.TaskResponse;
import dev.andzwp.taskservice.model.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service("task-response-mapper")
@RequiredArgsConstructor
public class TaskResponseMapper implements TaskMapper<TaskResponse> {

    private final ModelMapper modelMapper;

    @Override
    public TaskResponse mapEntityToDTO(Task task) {
        return modelMapper.map(task, TaskResponse.class);
    }

    @Override
    public Task mapDTOtoEntity(TaskResponse dto) {
        return modelMapper.map(dto, Task.class);
    }
}
