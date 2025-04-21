package edu.miu.cs.cs489.satcontrol.service.impl;

import edu.miu.cs.cs489.satcontrol.dto.request.SatelliteRequestDto;
import edu.miu.cs.cs489.satcontrol.dto.response.SatelliteResponseDto;
import edu.miu.cs.cs489.satcontrol.mapper.SatelliteMapper;
import edu.miu.cs.cs489.satcontrol.model.OrbitType;
import edu.miu.cs.cs489.satcontrol.model.Satellite;
import edu.miu.cs.cs489.satcontrol.repository.SatelliteRepository;
import edu.miu.cs.cs489.satcontrol.service.SatelliteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SatelliteServiceImpl implements SatelliteService {

    private final SatelliteRepository satelliteRepository;
    private final SatelliteMapper satelliteMapper;

    @Override
    public SatelliteResponseDto updateSatellite(Long id, SatelliteRequestDto dto) {
        Satellite existing = satelliteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Satellite not found with id: " + id));

        if (existing.isDecommissioned()) {
            throw new UnsupportedOperationException("Cannot update a decommissioned satellite");
        }

        OrbitType orbitType;
        try {
            orbitType = OrbitType.valueOf(dto.orbitType());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid orbit type: " + dto.orbitType());
        }

        existing.setName(dto.name());
        existing.setLaunchDate(dto.launchDate());
        existing.setOrbitType(orbitType);
        existing.setDecommissioned(dto.decommissioned());

        Satellite updated = satelliteRepository.save(existing);
        return satelliteMapper.toResponseDto(updated);
    }

    @Override
    public List<SatelliteResponseDto> getAllSatellites() {
        return satelliteMapper.toResponseDtoList(satelliteRepository.findAll());
    }
}
