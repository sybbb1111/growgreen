package com.green.growgreen.todo;

import com.green.growgreen.todo.model.TodoInsDto;
import com.green.growgreen.todo.model.TodoUpdDto;
import com.green.growgreen.todo.model.TodoVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @Operation(summary = "모든 Todo리스트 보기 ")
    public List<TodoVo> getTodo() {
        return SERVICE.getTodo();
    }

    @GetMapping("/{deadline}")
    @Operation(summary = "특정날짜의 Todo 목록 보기", description = "deadline = 2023-06-26")
    public List<TodoVo> getTodoByDay(@RequestParam String deadline) {
        return SERVICE.getTodoByDay(deadline);

    }

    @PutMapping
    @Operation(summary = "Todo 수정")
    public int putTodo(@RequestBody TodoUpdDto dto) {
        return SERVICE.putTodo(dto);
    }

    @DeleteMapping
    @Operation(summary = "Todo 삭제")
    public int deleteTodo(@RequestParam int itodo) {
        return 1;
    }

}
