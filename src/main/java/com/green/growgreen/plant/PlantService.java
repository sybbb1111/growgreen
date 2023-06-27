package com.green.growgreen.plant;


import com.green.growgreen.plant.model.*;
import com.green.growgreen.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantService {
    @Value("D:/download/growgreen/")
    private String fileDir;

    private final PlantMapper MAPPER;

    public int insPlant(MultipartFile img, PlantInsDto dto) {
        PlantEntity entity = new PlantEntity();
        entity.setNm(dto.getNm());
        entity.setNickNm(dto.getNickNm());
        entity.setPlantPic(dto.getPlantPic());
        entity.setOnDate(dto.getOnDate());
        entity.setCtnt(dto.getCtnt());

        // 랜덤 파일명 생성
        String savedFileNm = FileUtils.makeRandomFileNm(img.getOriginalFilename());
        System.out.println("savedFileNm : " + savedFileNm);

        // 랜덤파일명을 entity에 넣음
        entity.setPlantPic(savedFileNm);
        int result = MAPPER.insPlant(entity);

        // pk값 얻기
        System.out.println("result : " + result);
        System.out.println("pk : " + entity.getIplant());

        // D:/download/growgreen/plant/pk 값 폴더 생성
        String targetDir = String.format("%s/plant/%d", fileDir, entity.getIplant());
        File fileTargetDir = new File(targetDir);

        // 없으면 폴더 만든다
        if(!fileTargetDir.exists()) {
            fileTargetDir.mkdirs();
        }

        // 이미지를 해당 폴더로 옮긴다
        File fileTarget = new File(targetDir + "/" + savedFileNm);
        try {
            img.transferTo(fileTarget);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;

    }




    public int updPlant(MultipartFile img, PlantUpdDto dto) {

        String savedFileNm = FileUtils.makeRandomFileNm(img.getOriginalFilename());

        dto.setPlantPic(savedFileNm);

        int result = MAPPER.updPlant(dto);

        String targetDir = String.format("%s/plant/%d", fileDir, dto.getIplant());
        File fileTargetDir = new File(targetDir);

        if(!fileTargetDir.exists()) {
            fileTargetDir.mkdirs();
        }
        File fileTarget = new File(targetDir + "/" + savedFileNm);
        try {
            img.transferTo(fileTarget);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;


    }






    public int delPlant(PlantDelDto dto) {
        return MAPPER.delPlant(dto);
    }

    public List<PlantVo> selPlant() {
        return MAPPER.selPlant();
    }

    public PlantSelDetailVo selPlantDetail(int iplant) {
        return MAPPER.selPlantDetail(iplant);
    }


}
