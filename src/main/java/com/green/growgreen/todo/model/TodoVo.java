package com.green.growgreen.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoVo {
    private int itodo;
    private int iplant;
    private String ctnt;
    private String deadline;
    private String finishYn;

}
