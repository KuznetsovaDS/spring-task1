package com.javarush.kuznetsova.repo;

import com.javarush.kuznetsova.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository <Task, Integer> {
}
