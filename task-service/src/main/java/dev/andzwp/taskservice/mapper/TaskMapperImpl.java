package dev.andzwp.taskservice.mapper;

import dev.andzwp.taskservice.dto.TaskDTO;
import dev.andzwp.taskservice.model.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskMapperImpl implements TaskMapper {

    private final ModelMapper modelMapper;

    @Override
    public TaskDTO mapEntityToDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public Task mapDTOtoEntity(TaskDTO dto) {
        return modelMapper.map(dto, Task.class);
    }
}
