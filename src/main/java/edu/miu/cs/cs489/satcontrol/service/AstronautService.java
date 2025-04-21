package edu.miu.cs.cs489.satcontrol.service;

import edu.miu.cs.cs489.satcontrol.dto.request.AstronautRequestDto;
import edu.miu.cs.cs489.satcontrol.dto.response.AstronautResponseDto;

import java.util.List;

public interface AstronautService {
    AstronautResponseDto createAstronaut(AstronautRequestDto dto);
    List<AstronautResponseDto> getAllAstronautsSorted(String sortBy);
}
