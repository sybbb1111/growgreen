package com.green.growgreen.diary;

import com.green.growgreen.diary.model.*;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "다이어리 등록")
    public int postDiary(@RequestBody DiaryInsDto dto){
        return SERVICE.postDiary(dto);
    }

    @DeleteMapping
    @Operation(summary = "다이어리 삭제")
    public int delDiary (@RequestParam int idiary) {
        DiaryDelDto dto = new DiaryDelDto();
        dto.setIdiary(idiary);
        return SERVICE.delDiary(dto);
    }

    @PutMapping
    @Operation(summary = "다이어리 수정")
    public int updDiary(@RequestBody DiaryUpdDto dto) {
        return SERVICE.updDiary(dto);
    }

    @GetMapping
    @Operation(summary = "모든 다이어리 보기")
    public List<DiarySelAllVo> selAllVoList (){
        return SERVICE.selDiary();
    }

    @GetMapping("/{idiary}")
    @Operation(summary = "다이어리 디테일 보기")
    public DiarySelAllVo selDiaryDetail (@PathVariable int idiary) {
        DiarySelDetailDto dto = new DiarySelDetailDto();
        dto.setIdiary(idiary);
        return SERVICE.selById(dto);
    }
 }
