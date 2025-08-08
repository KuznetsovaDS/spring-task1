package com.javarush.kuznetsova.mapper;

import com.javarush.kuznetsova.dto.TaskReadDto;
import com.javarush.kuznetsova.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskReadMapper implements Mapper<Task, TaskReadDto> {

    @Override
    public TaskReadDto map(Task obj) {
        return new TaskReadDto(
                obj.getId(),
                obj.getDescription(),
                obj.getStatus()
        );
    }
}
