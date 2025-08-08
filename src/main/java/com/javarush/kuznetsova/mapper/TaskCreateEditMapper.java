package com.javarush.kuznetsova.mapper;

import com.javarush.kuznetsova.dto.TaskCreateEditDto;
import com.javarush.kuznetsova.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskCreateEditMapper implements Mapper<TaskCreateEditDto, Task> {
    @Override
    public Task map(TaskCreateEditDto obj) {
        Task task = new Task();
        task.setDescription(obj.getDescription());
        task.setStatus(obj.getStatus());
        return task;
    }

    @Override
    public Task map(TaskCreateEditDto fromObj, Task toObj) {
        toObj.setDescription(fromObj.getDescription());
        toObj.setStatus(fromObj.getStatus());
        return toObj;
    }
}
