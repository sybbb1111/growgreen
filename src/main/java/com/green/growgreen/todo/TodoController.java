package com.green.growgreen.todo;

import com.green.growgreen.todo.model.TodoInsDto;
import com.green.growgreen.todo.model.TodoUpdDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService SERVICE;

    @Autowired
    public TodoController(TodoService service) {
        this.SERVICE = service;
    }

    @PostMapping
    @Operation(summary = "Todo 등록")
    public int postTodo(@RequestBody TodoInsDto dto) {
        return SERVICE.postTodo(dto);
    }

    @PutMapping
    @Operation(summary = "Todo 수정")
    public int putTodo(@RequestBody TodoUpdDto dto) {
        return SERVICE.putTodo(dto);
    }

}
