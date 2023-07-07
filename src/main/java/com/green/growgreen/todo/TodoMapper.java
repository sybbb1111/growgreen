package com.green.growgreen.todo;

import com.green.growgreen.todo.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {

    int insTodo(TodoEntity entity);
    int insRepeatDay(TodoRepeatDayDto repeatDayDto);

    List<TodoVo> selTodo();
    List<TodoVo> selTodoByDay(TodoSelDto dto);
    List<TodoVo> selTodoAll();

    TodoDetailVo selTodoDetail(int itodo);
    List<String> selTodoRepeatDay(int itodo);

    int updTodo(TodoUpdDto dto);
    int updTodoRepeatDay(TodoRepeatDayDto repeatDayDto);

    int delTodo(TodoDelDto dto);
    int delRepeatDay(TodoDelDto dto);

    List<TodoSelRepeatDayVo> selRepeatTodo ();
    int insUpdRepeatDay (int day);
}
