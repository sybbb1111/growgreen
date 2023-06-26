package com.green.growgreen.plant;

import com.green.growgreen.plant.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plant")
@RequiredArgsConstructor
public class PlantController {
    private final PlantService SERVICE;

    @PostMapping
    public int postPlant(@RequestBody PlantInsDto dto){
        return SERVICE.insPlant(dto);
    }

    @PutMapping
    public int putPlant(@RequestBody PlantUpdDto dto){
        return SERVICE.updPlant(dto);
    }

    @DeleteMapping
    public int deletePlant(@RequestBody PlantDelDto dto) {
        return SERVICE.delPlant(dto);
    }

    @GetMapping
    public List<PlantVo> getPlant() {
        return SERVICE.selPlant();
    }

    @GetMapping("/{iplant}")
    public PlantSelDetailVo selPlantDetail(@PathVariable int iplant) {
        return SERVICE.selPlantDetail(iplant);
    }

}
