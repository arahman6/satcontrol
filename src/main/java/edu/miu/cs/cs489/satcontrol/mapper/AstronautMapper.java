package edu.miu.cs.cs489.satcontrol.mapper;

import edu.miu.cs.cs489.satcontrol.dto.request.AstronautRequestDto;
import edu.miu.cs.cs489.satcontrol.dto.response.AstronautResponseDto;
import edu.miu.cs.cs489.satcontrol.dto.response.SatelliteResponseDto;
import edu.miu.cs.cs489.satcontrol.model.Astronaut;
import edu.miu.cs.cs489.satcontrol.model.Satellite;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AstronautMapper {
    AstronautResponseDto toResponseDto(Astronaut astronaut);

    List<AstronautResponseDto> toResponseDtoList(List<Astronaut> astronauts);
}
