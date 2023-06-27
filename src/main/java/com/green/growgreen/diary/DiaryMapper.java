package com.green.growgreen.diary;

import com.green.growgreen.diary.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryMapper {
    int insDiary (DiaryEntity entity);
    int delDiary (DiaryDelDto dto);
    int updDiary (DiaryUpdDto dto);
    List<DiarySelAllVo> selDiaryAll ();
    DiarySelAllVo selDiaryById (DiarySelDetailDto dto);

    int insDiaryPic (List<DiaryPicEntity> entity);
    List<String> selDiaryDetailPics(DiarySelDetailDto dto);

}
