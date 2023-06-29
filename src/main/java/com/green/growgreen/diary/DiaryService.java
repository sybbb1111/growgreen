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

        DiaryEntity entity = new DiaryEntity();
        entity.setCtnt(dto.getCtnt());
        entity.setTitle(dto.getTitle());

        String saveFileName = FileUtils.makeRandomFileNm(pic.getOriginalFilename());

        entity.setPic(saveFileName);
        int result = MAPPER.insDiary(entity);

        String centPath = String.format("diaryPics/%d", entity.getIdiary());
        String targetDir = String.format("%s/%s", fileDir,centPath);

        File file = new File(targetDir);
        if (!file.exists()){
            file.mkdirs();
        }

        File fileTarget = new File(targetDir + "/" + saveFileName);

        try {
            pic.transferTo(fileTarget);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
    // 다중 파일 업로드 하기
    public int insDiaryPics (int idiary,
                             List<MultipartFile> pics) throws Exception  {

        String centPath = String.format("diaryPics/%d", idiary);
        String targetDir = String.format("%s/%s", fileDir,centPath);

        File file = new File(targetDir);
        if (!file.exists()){
            file.mkdirs();
        }

        List<DiaryPicEntity> list = new ArrayList<>();

        for (MultipartFile multipartFile : pics) {

            String originFile = multipartFile.getOriginalFilename();
            String saveName = FileUtils.makeRandomFileNm(originFile);

            File fileTarget = new File(targetDir + "/" + saveName);

            try {
                multipartFile.transferTo(fileTarget);
            } catch (IOException e) {
                throw new Exception("파일 저장을 실패하였습니다.");
            }

            DiaryPicEntity entity = new DiaryPicEntity();
            entity.setPic(saveName);
            entity.setIdiary(idiary);
            list.add(entity);
        }

        return MAPPER.insDiaryPic(list);
    }

    // 다이어리 다중 파일 삭제
    public int putDiaryPics (DiaryDelSubPicsDto dto)   {
        return MAPPER.delDiarySubPic(dto);
    }

    // 썸네일과 내용 수정하기
    public int updDiary (MultipartFile pic, DiaryUpdDto dto){

        DiaryEntity entity = new DiaryEntity();
        entity.setIdiary(dto.getIdiary());
        entity.setCtnt(dto.getCtnt());
        entity.setTitle(dto.getTitle());

        String saveFileName = FileUtils.makeRandomFileNm(pic.getOriginalFilename());
        entity.setPic(saveFileName);

        int result = MAPPER.updDiary(entity);

        String centPath = String.format("diaryPics/%d", dto.getIdiary());
        String targetDir = String.format("%s/%s", fileDir,centPath);

        File file = new File(targetDir);
        if (!file.exists()){
            file.mkdirs();
        }

        File fileTarget = new File(targetDir + "/" + saveFileName);

        try {
            pic.transferTo(fileTarget);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    // 전체 리스트 보기
    public List<DiarySelAllVo> selDiary(){
        return MAPPER.selDiaryAll();
    }

    // 하나의 디테일 다이어리 보기
    public DiarySelAllVo selById (DiarySelDetailDto dto) {
        return MAPPER.selDiaryById(dto);
    }

    // 하나의 디테일한 리스트와 그 하위 이미지들 보기
    public DiaryAllDto selDiaryPicData (DiarySelDetailDto dto) {

        DiarySelAllVo data = MAPPER.selDiaryById(dto);
        List<String> pics = MAPPER.selDiaryDetailPics(dto);
        return DiaryAllDto
                .builder()
                .data(data)
                .pics(pics)
                .build();
    }

    // 다이어리 삭제 다중이미지도 함께 삭제
    public int delDiary (DiaryDelDto dto) {
        return MAPPER.delDiary(dto);
    }
}
