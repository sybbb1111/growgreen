package com.green.growgreen.diary;

import com.green.growgreen.diary.model.DiaryInsDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiaryMapper {
    int insDiary (DiaryInsDto dto);
}
