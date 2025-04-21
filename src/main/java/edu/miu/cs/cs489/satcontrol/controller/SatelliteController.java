package edu.miu.cs.cs489.satcontrol.controller;

import edu.miu.cs.cs489.satcontrol.dto.request.SatelliteRequestDto;
import edu.miu.cs.cs489.satcontrol.dto.response.SatelliteResponseDto;
import edu.miu.cs.cs489.satcontrol.exception.SatelliteNotFoundException;
import edu.miu.cs.cs489.satcontrol.service.SatelliteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/satellites")
//@RequiredArgsConstructor
public class SatelliteController {


    private final SatelliteService satelliteService;

    public SatelliteController(SatelliteService satelliteService) {
        this.satelliteService = satelliteService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<SatelliteResponseDto> updateSatellite(
            @PathVariable Long id,
            @Valid @RequestBody SatelliteRequestDto satelliteRequestDto) {
        SatelliteResponseDto updated = satelliteService.updateSatellite(id, satelliteRequestDto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<SatelliteResponseDto>> getAllSatellites() {
        return ResponseEntity.ok(satelliteService.getAllSatellites());
    }
}
