package dev.andzwp.taskservice.mapper;


import dev.andzwp.taskservice.dto.TaskRequest;
import dev.andzwp.taskservice.model.Task;

public interface TaskMapper<T> {

    T mapEntityToDTO(Task task);

    Task mapDTOtoEntity(T dto);

}
