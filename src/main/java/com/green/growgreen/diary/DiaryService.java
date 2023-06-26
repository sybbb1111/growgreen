package com.green.growgreen.diary;

import com.green.growgreen.diary.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryMapper MAPPER;

    public int postDiary (DiaryInsDto dto){
        return MAPPER.insDiary(dto);
    }

    public int delDiary (DiaryDelDto dto) {
        return MAPPER.delDiary(dto);
    }

    public int updDiary (DiaryUpdDto dto){
        return MAPPER.updDiary(dto);
    }

    public List<DiarySelAllVo> selDiary(){
        return MAPPER.selDiaryAll();
    }

    public DiarySelAllVo selById (DiarySelDetailDto dto) {
        return MAPPER.selDiaryById(dto);
    }
}
