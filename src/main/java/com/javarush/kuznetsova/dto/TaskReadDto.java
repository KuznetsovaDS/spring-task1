package com.javarush.kuznetsova.dto;

import com.javarush.kuznetsova.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class TaskReadDto {

    Integer id;
    String description;
    Status status;
}
