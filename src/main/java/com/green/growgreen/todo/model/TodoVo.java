package com.green.growgreen.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoVo {
    private String ctnt;
    private String month;
    private String time;
    private String nickNm;
    private String nm;
    private int finishYn;
}
