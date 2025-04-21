package edu.miu.cs.cs489.satcontrol.dto.response;

import java.util.List;

public record AstronautResponseDto(
        Long id,
        String firstName,
        String lastName,
        int experienceYears,
        List<SatelliteResponseDto> satellites
) {}
