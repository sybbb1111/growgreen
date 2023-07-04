package com.green.growgreen.todo;

import com.green.growgreen.todo.model.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService SERVICE;

    @Autowired
    public TodoController(TodoService service) {
        this.SERVICE = service;
    }

    @PostMapping
    @Operation(summary = "Todo 등록", description = "deadline 형식 = 시:분")
    public int postTodo(@RequestBody TodoInsDto dto) {
        return SERVICE.postTodo(dto);
    }

    @GetMapping
    @Operation(summary = "deadline이 오늘날짜인 Todo리스트 보기 ")
    public List<TodoVo> getTodo() {
        return SERVICE.getTodo();
    }

    @GetMapping("/{deadline}")
    @Operation(summary = "특정날짜의 Todo 목록 보기", description = "deadline 형식 = 2023-06-26")
    public List<TodoVo> getTodoByDay(@PathVariable String deadline) {
        return SERVICE.getTodoByDay(deadline);
    }

    @GetMapping("/list")
    @Operation(summary = "전체 Todo리스트 보기", description = "finishYn =0(완료되지 않은 투두), delYn=0(삭제되지 않은 투두)만 출력")
    public List<TodoVo> getTodoAll() {
        return SERVICE.getTodoAll();
    }

    @PutMapping
    @Operation(summary = "Todo 수정")
    public int putTodo(@RequestBody TodoUpdDto dto) {
        return SERVICE.putTodo(dto);
    }

    @DeleteMapping
    @Operation(summary = "Todo 삭제", description = "반복 있는 Todo 삭제시 반복요일(repeatDay) 데이터도 삭제")
    public int deleteTodo(@RequestBody TodoDelDto dto) {

        return SERVICE.deleteTodo(dto);
    }


}
