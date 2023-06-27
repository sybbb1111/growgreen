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
    public int insDiaryPics (int idiary, List<MultipartFile> pics) throws Exception  {

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

        return Integer.valueOf(MAPPER.insDiaryPic(list));
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

    public DiaryAllDto selDiaryPicData (DiarySelDetailDto dto) {

        DiarySelAllVo data = MAPPER.selDiaryById(dto);
        List<String> pics = MAPPER.selDiaryDetailPics(dto);
        return DiaryAllDto
                .builder()
                .data(data)
                .pics(pics)
                .build();
    }
}
