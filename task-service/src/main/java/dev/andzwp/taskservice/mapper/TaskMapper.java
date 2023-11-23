package dev.andzwp.taskservice.mapper;


import dev.andzwp.taskservice.dto.TaskDTO;
import dev.andzwp.taskservice.model.Task;

public interface TaskMapper {

    TaskDTO mapEntityToDTO(Task task);

    Task mapDTOtoEntity(TaskDTO dto);

}
