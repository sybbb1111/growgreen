package com.green.growgreen.todo.model;

import lombok.Data;

import java.util.List;

@Data
public class TodoInsDto {
    private int iplant;
    private String ctnt;
    private String deadline;
    private String repeatYn;
    private List<String> repeatDay;
}
