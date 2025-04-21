package edu.miu.cs.cs489.satcontrol.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record SatelliteRequestDto(
        @NotBlank
        String name,

        @Past
        LocalDate launchDate,

        @NotBlank
        @Pattern(regexp = "LEO|MEO|GEO", message = "OrbitType must be one of: LEO, MEO, GEO")
        String orbitType,

        boolean decommissioned
) {}