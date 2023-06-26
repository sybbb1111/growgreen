package com.green.growgreen.diary;

import com.green.growgreen.diary.model.DiaryInsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryMapper MAPPER;

    public int postDiary (DiaryInsDto dto){
        return MAPPER.insDiary(dto);
    }
}
