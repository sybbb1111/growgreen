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

            repeatDto.setItodo(entity.getItodo());

            for(int i=0; i<dto.getRepeatDay().size(); i++) {
                String repeatDay = dto.getRepeatDay().get(i);
                repeatDto.setRepeatDay(repeatDay);
                MAPPER.insRepeatDay(repeatDto);
            }

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
        //repeatYn이 0이나 1 모두 p_todo테이블의 todo데이터를 수정
        MAPPER.updTodo(dto); // p_todo테이블에서 todo 수정

        //repeatYn = 0 인 경우
        if( dto.getRepeatYn().equals("0") ) {
            TodoDelDto delDto = new TodoDelDto();
            delDto.setItodo(dto.getItodo()); // repeatYn=1에서 0으로 바뀐 경우에는 p_day에 있는 반복 데이터를 지워야하니깐 필요한 작업
            MAPPER.delRepeatDay(delDto);
        }

        //repeatYn = 1 인 경우를 if문으로 먼저 확인
        if( dto.getRepeatYn().equals("1") ) {
            TodoRepeatDayDto repeatDto = new TodoRepeatDayDto();

            repeatDto.setItodo(dto.getItodo());
//            repeatDto.setRepeatDay(dto.getRepeatDay());

            //p_day테이블에 있는 기존 반복 데이터 삭제
            TodoDelDto delDto = new TodoDelDto();
            delDto.setItodo(dto.getItodo());
            MAPPER.delRepeatDay(delDto);

            // for문으로 선택한 반복요일 만큼 p_day테이블에 insert
            for(int i=0; i<dto.getRepeatDay().size(); i++) {
                String repeatDay = dto.getRepeatDay().get(i);
                repeatDto.setRepeatDay(repeatDay);
                MAPPER.insRepeatDay(repeatDto);
            }
        }
        return 1;

    }

    public int deleteTodo(TodoDelDto dto) {
        // delYn = 1 인 경우 t_day에서도 데이터 삭제
        if(dto.getDelYn().equals("1")) {
            MAPPER.delRepeatDay(dto);
        }
        // todo 삭제
        return MAPPER.delTodo(dto);
    }


}
