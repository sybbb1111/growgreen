package com.green.growgreen.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
@Data
public class TodoVo {
    private String ctnt;
    private String deadlineDate;
    private String deadlineTime;
    private String nickNm;
    private String nm;
    private int finishYn;
}
