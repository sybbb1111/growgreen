package com.green.growgreen.diary;

import com.green.growgreen.diary.model.*;
import com.green.growgreen.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryMapper MAPPER;

    @Value("${file.dir}")
    private String fileDir;

    // 썸네일 이미지와 처음 insert 하는 부분
    public int postDiary (MultipartFile pic, DiaryInsDto dto){
       return 0;
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
