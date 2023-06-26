package com.green.growgreen.diary;

import com.green.growgreen.diary.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService SERVICE;

    @PostMapping
    public int postDiary(@RequestBody DiaryInsDto dto){
        return SERVICE.postDiary(dto);
    }

    @DeleteMapping
    public int delDiary (@RequestParam int idiary) {
        DiaryDelDto dto = new DiaryDelDto();
        dto.setIdiary(idiary);
        return SERVICE.delDiary(dto);
    }

    @PutMapping
    public int updDiary(@RequestBody DiaryUpdDto dto) {
        return SERVICE.updDiary(dto);
    }

    @GetMapping
    public List<DiarySelAllVo> selAllVoList (){
        return SERVICE.selDiary();
    }

    @GetMapping("/{idiary}")
    public DiarySelAllVo selDiaryDetail (@PathVariable int idiary) {
        DiarySelDetailDto dto = new DiarySelDetailDto();
        dto.setIdiary(idiary);
        return SERVICE.selById(dto);
    }
 }
