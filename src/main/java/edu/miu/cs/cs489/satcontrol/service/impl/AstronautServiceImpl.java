package edu.miu.cs.cs489.satcontrol.service.impl;

import edu.miu.cs.cs489.satcontrol.dto.request.AstronautRequestDto;
import edu.miu.cs.cs489.satcontrol.dto.response.AstronautResponseDto;
import edu.miu.cs.cs489.satcontrol.mapper.AstronautMapper;
import edu.miu.cs.cs489.satcontrol.model.Astronaut;
import edu.miu.cs.cs489.satcontrol.model.Satellite;
import edu.miu.cs.cs489.satcontrol.repository.AstronautRepository;
import edu.miu.cs.cs489.satcontrol.repository.SatelliteRepository;
import edu.miu.cs.cs489.satcontrol.service.AstronautService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AstronautServiceImpl implements AstronautService {

    private final AstronautRepository astronautRepository;
    private final SatelliteRepository satelliteRepository;
    private final AstronautMapper astronautMapper;

    @Override
    public AstronautResponseDto createAstronaut(AstronautRequestDto dto) {
        List<Satellite> satellites = satelliteRepository.findAllById(dto.satelliteIds());

        if (satellites.size() != dto.satelliteIds().size()) {
            throw new IllegalArgumentException("One or more satellite IDs are invalid");
        }
        System.out.println(Astronaut.builder());

        Astronaut astronaut = Astronaut.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .experienceYears(dto.experienceYears())
                .satellites(new HashSet<>(satellites))
                .build();

        Astronaut saved = astronautRepository.save(astronaut);
        return astronautMapper.toResponseDto(saved);
    }

    @Override
    public List<AstronautResponseDto> getAllAstronautsSorted(String sortBy) {
        List<Astronaut> astronauts = astronautRepository.findAll(Sort.by(sortBy));
        return astronautMapper.toResponseDtoList(astronauts);
    }
    @Override
    public AstronautResponseDto updateAstronaut(Long id, AstronautRequestDto dto) {
        Astronaut existing = astronautRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Astronaut not found with id: " + id));

        List<Satellite> satellites = satelliteRepository.findAllById(dto.satelliteIds());
        if (satellites.size() != dto.satelliteIds().size()) {
            throw new IllegalArgumentException("One or more satellite IDs are invalid");
        }

        existing.setFirstName(dto.firstName());
        existing.setLastName(dto.lastName());
        existing.setExperienceYears(dto.experienceYears());
        existing.setSatellites(new HashSet<>(satellites));

        Astronaut updated = astronautRepository.save(existing);
        return astronautMapper.toResponseDto(updated);
    }

    @Override
    public void deleteAstronaut(Long id) {
        if (!astronautRepository.existsById(id)) {
            throw new IllegalArgumentException("Astronaut not found with id: " + id);
        }
        astronautRepository.deleteById(id);
    }

}
