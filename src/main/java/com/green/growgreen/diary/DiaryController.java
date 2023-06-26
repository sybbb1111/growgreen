package com.green.growgreen.diary;

import com.green.growgreen.diary.model.DiaryInsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService SERVICE;

    @PostMapping
    public int postDiary(@RequestBody DiaryInsDto dto){
        return SERVICE.postDiary(dto);
    }
}
