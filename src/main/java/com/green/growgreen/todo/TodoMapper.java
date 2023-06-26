package com.green.growgreen.todo;

import com.green.growgreen.todo.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {

    int insTodo(TodoInsDto dto);
    List<TodoVo> selTodo();
    List<TodoVo> selTodoByDay(TodoSelDto dto);
    int updTodo(TodoUpdDto dto);
    int delTodo(TodoEntity entity);
}
