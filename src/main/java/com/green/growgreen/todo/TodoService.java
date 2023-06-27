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
        TodoEntity entity = new TodoEntity();
        entity.setIplant(dto.getIplant());
        entity.setCtnt(dto.getCtnt());
        entity.setDeadline(dto.getDeadline());
        entity.setRepeatYn(dto.getRepeatYn());
        //Todo등록
        MAPPER.insTodo(entity);

        // repeatYn이 1이면 p_day테이블에 인서트
        if( dto.getRepeatYn().equals("1") ) {
            TodoRepeatDayDto repeatDto = new TodoRepeatDayDto();

            //프론트에서 받은 String 1을 정수형으로 바꾸기
            int repeatIntDay = Integer.parseInt(dto.getRepeatDay());

            repeatDto.setRepeatDay(repeatIntDay);
            repeatDto.setItodo(entity.getItodo());

            MAPPER.insRepeatDay(repeatDto);

            return entity.getItodo();
        }
        return entity.getItodo();
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
        //필요한 로직. 반복요일이 없었던 투두를 수정해서 반복요일을 추가하는 경우 p_day에 인서트해야함

        //repeatYn = 0 인 경우
        MAPPER.updTodo(dto);

        //repeatYn = 1 인 경우를 if문으로 먼저 확인
        if( dto.getRepeatYn().equals("1") ) {
            TodoRepeatDayDto repeatDayDto = new TodoRepeatDayDto();

            //프론트에서 받은 String 1을 정수형으로 바꾸기
            int repeatIntDay = Integer.parseInt(dto.getRepeatDay());
            //repeatDayDto에 itodo, repeatDay 값 넣기
            repeatDayDto.setItodo(dto.getItodo());
            repeatDayDto.setRepeatDay(repeatIntDay);

            return MAPPER.updTodoRepeatDay(repeatDayDto);
        }
        return 1;

    }

    public int deleteTodo(int itodo) {
        TodoEntity entity = new TodoEntity();
        entity.setItodo(itodo);

        return MAPPER.delTodo(entity);
    }
}
