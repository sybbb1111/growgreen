package com.green.growgreen.todo;

import com.green.growgreen.todo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoMapper MAPPER;

    @Autowired
    public TodoService(TodoMapper mapper) {
        this.MAPPER = mapper;
    }

    public int postTodo(TodoInsDto dto) {
        return MAPPER.insTodo(dto);
    }

    public List<TodoVo> getTodo() {
        return MAPPER.selTodo();
    }

    public List<TodoVo> getTodoByDay(String deadline) {
        //프론트에서 넘어온 deadline의 값이 2023-06-06 형식인지 검사하는 로직 필요

        TodoSelDto dto = new TodoSelDto();
        dto.setDeadline(deadline);

        return MAPPER.selTodoByDay(dto);
    }

    public int putTodo(TodoUpdDto dto) {
        return MAPPER.updTodo(dto);
    }

    public int deleteTodo(int itodo) {
        TodoEntity entity = new TodoEntity();
        entity.setItodo(itodo);

        return MAPPER.delTodo(entity);
    }
}
