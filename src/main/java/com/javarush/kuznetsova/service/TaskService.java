package com.javarush.kuznetsova.service;

import com.javarush.kuznetsova.dto.TaskCreateEditDto;
import com.javarush.kuznetsova.dto.TaskReadDto;
import com.javarush.kuznetsova.entity.Task;
import com.javarush.kuznetsova.mapper.TaskCreateEditMapper;
import com.javarush.kuznetsova.mapper.TaskReadMapper;
import com.javarush.kuznetsova.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final TaskReadMapper taskReadMapper;
    private final TaskCreateEditMapper taskCreateEditMapper;

    public Page<TaskReadDto> findAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> taskPage = taskRepo.findAll(pageable);
        return taskPage.map(taskReadMapper::map);
    }
    public Optional<TaskReadDto> findById(Integer id){
        return taskRepo.findById(id)
                .map(taskReadMapper::map);
    }
    @Transactional
    public TaskReadDto create(TaskCreateEditDto dto){
        return Optional.of(dto)
                .map(taskCreateEditMapper::map)
                .map(taskRepo::save)
                .map(taskReadMapper::map)
                .orElseThrow();
    }
    @Transactional
    public Optional<TaskReadDto> update(Integer id, TaskCreateEditDto dto){
        return taskRepo.findById(id)
                .map(entity -> taskCreateEditMapper.map(dto, entity))
                .map(taskRepo::saveAndFlush)
                .map(taskReadMapper::map);
    }
    @Transactional
    public boolean delete(Integer id){
        return taskRepo.findById(id)
                .map(entity ->{
                    taskRepo.delete(entity);
                    taskRepo.flush();
                    return true;
                })
                .orElse(false);
    }
}
