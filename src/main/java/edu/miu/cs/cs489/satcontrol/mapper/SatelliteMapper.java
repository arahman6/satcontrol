package edu.miu.cs.cs489.satcontrol.mapper;

import edu.miu.cs.cs489.satcontrol.dto.request.SatelliteRequestDto;
import edu.miu.cs.cs489.satcontrol.dto.response.SatelliteResponseDto;
import edu.miu.cs.cs489.satcontrol.model.Satellite;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SatelliteMapper {
    SatelliteResponseDto toResponseDto(Satellite satellite);

    List<SatelliteResponseDto> toResponseDtoList(List<Satellite> satellites);

    Satellite toEntity(SatelliteRequestDto dto);
}
