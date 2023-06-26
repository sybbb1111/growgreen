package com.green.growgreen.todo.model;

import lombok.Data;

@Data
public class TodoUpdDto {
    private int itodo;
    private int iplant;
    private String deadline;
    private String ctnt;
    private String repeatYn;
    private String repeatDay;

}
