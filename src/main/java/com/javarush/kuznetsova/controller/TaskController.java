package com.javarush.kuznetsova.controller;

import com.javarush.kuznetsova.dto.TaskCreateEditDto;
import com.javarush.kuznetsova.dto.TaskReadDto;
import com.javarush.kuznetsova.entity.Status;
import com.javarush.kuznetsova.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "size", defaultValue = "5") int size,
                          Model model){
        try{
            Page<TaskReadDto> tasks = taskService.findAll(page, size);
            model.addAttribute("tasks", tasks.getContent());
            model.addAttribute("totalPages", tasks.getTotalPages());
            model.addAttribute("currentPage", page);
            model.addAttribute("hasNext", tasks.hasNext());
            model.addAttribute("hasPrevious", tasks.hasPrevious());
            return "task/tasks";
        }
        catch (Exception e){
            log.error("Ошибка при получении списка задач", e);
            model.addAttribute("error", "Ошибка при загрузке задач");
            return "error/error";
        }
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model){
       try{
           return taskService.findById(id)
                   .map(task -> {
                       model.addAttribute("task", task);
                       model.addAttribute("status", Status.values());
                       return "task/task";
                   })
                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
       }catch (Exception e){
           log.error("Ошибка при получении задачи с id: " + id, e);
           model.addAttribute("error", "Ошибка при загрузке задачи");
           return "error/error";
       }
    }
    @GetMapping("/creation")
    public String create(Model model, @ModelAttribute("taskCreateEditDto") TaskCreateEditDto taskCreateEditDto){
        model.addAttribute("status", Status.values());
        return "task/creation";
    }

    @PostMapping
    public String create(@ModelAttribute("taskCreateEditDto") TaskCreateEditDto taskCreateEditDto){
        return "redirect:/" + taskService.create(taskCreateEditDto).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute ("taskCreateEditDto") TaskCreateEditDto taskCreateEditDto){
        return taskService.update(id, taskCreateEditDto)
                .map(it -> "redirect:/" + id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        try {
            if (!taskService.delete(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return "redirect:/";
        } catch (Exception e) {
            log.error("Ошибка при удалении задачи с id: " + id, e);
            return "redirect:/";
        }
    }
}
