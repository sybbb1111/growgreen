package com.green.growgreen.todo.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class TodoUpdVo {
    private int itodo;
    private int iplant;
    private String deadlineTime;
    private String deadlineDate;
    private String ctnt;
    private int repeatYn;
    private int repeatDay;

}
