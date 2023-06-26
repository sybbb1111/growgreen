package com.green.growgreen.plant;


import com.green.growgreen.plant.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantService {
    private final PlantMapper MAPPER;

    public int insPlant(PlantInsDto dto) {
        return MAPPER.insPlant(dto);
    }

    public int updPlant(PlantUpdDto dto) {
        return MAPPER.updPlant(dto);
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
