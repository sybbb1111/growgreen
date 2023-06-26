package com.green.growgreen.todo.model;

import lombok.Data;

@Data
public class TodoEntity {
    private int itodo;
    private int iplant;
    private String ctnt;
    private String deadline;
    private String createdAt;
    private String updatedAt;
    private String delYn;
    private String finishYn;
    private String finishedAt;
    private String repeatYn;
}
