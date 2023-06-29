package com.green.growgreen.diary;

import com.green.growgreen.diary.model.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService SERVICE;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "다이어리 등록, 메인사진과 함께 등록")
    public int postDiary(@RequestPart MultipartFile pic,
                         @RequestPart DiaryInsDto dto){
        return SERVICE.postDiary(pic, dto);
    }

    @DeleteMapping
    @Operation(summary = "다이어리 삭제")
    public int delDiary (@RequestParam int idiary) {
        DiaryDelDto dto = new DiaryDelDto();
        dto.setIdiary(idiary);
        return SERVICE.delDiary(dto);
    }
    @PostMapping(value = "/{idiary}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "다이어리 파일 다중 업로드")
    public int insDiaryPic (@PathVariable int idiary,
                            @RequestPart List<MultipartFile> pics) throws Exception {
        return SERVICE.insDiaryPics(idiary, pics);
    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "다이어리 수정")
    public int updDiary(@RequestPart MultipartFile pic,
                        @RequestPart DiaryUpdDto dto) {
        return SERVICE.updDiary(pic, dto);
    }

    @PutMapping("/{ipic}")
    @Operation(summary = "다이어리 다중 이미지 삭제")
    public int updDiarySubPic(@PathVariable int ipic){
        DiaryDelSubPicsDto dto = new DiaryDelSubPicsDto();
        dto.setIpic(ipic);
        return SERVICE.putDiaryPics(dto);
    }

    @GetMapping
    @Operation(summary = "모든 다이어리 보기")
    public List<DiarySelAllVo> selAllVoList (){
        return SERVICE.selDiary();
    }

    @GetMapping("/{idiary}")
    @Operation(summary = "다이어리 디테일 보기")
    public DiaryAllDto selDiaryDetail (@PathVariable int idiary) {
        return SERVICE.selDiaryPicData(new DiarySelDetailDto (idiary));
    }
 }
