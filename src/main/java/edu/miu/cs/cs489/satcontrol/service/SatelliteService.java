package edu.miu.cs.cs489.satcontrol.service;

import edu.miu.cs.cs489.satcontrol.dto.request.SatelliteRequestDto;
import edu.miu.cs.cs489.satcontrol.dto.response.SatelliteResponseDto;

import java.util.List;

public interface SatelliteService {
    SatelliteResponseDto updateSatellite(Long id, SatelliteRequestDto requestDto);
    List<SatelliteResponseDto> getAllSatellites();
}
