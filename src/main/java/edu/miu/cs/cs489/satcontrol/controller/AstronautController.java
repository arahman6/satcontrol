package edu.miu.cs.cs489.satcontrol.controller;

import edu.miu.cs.cs489.satcontrol.dto.request.AstronautRequestDto;
import edu.miu.cs.cs489.satcontrol.dto.response.AstronautResponseDto;
import edu.miu.cs.cs489.satcontrol.service.AstronautService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/astronauts")
public class AstronautController {

    private final AstronautService astronautService;

    public AstronautController(AstronautService astronautService) {
        this.astronautService = astronautService;
    }

    // ✅ Create
    @PostMapping
    public ResponseEntity<AstronautResponseDto> createAstronaut(
            @Valid @RequestBody AstronautRequestDto astronautRequestDto) {
        AstronautResponseDto saved = astronautService.createAstronaut(astronautRequestDto);
        return ResponseEntity.status(201).body(saved);
    }

    // ✅ Get all sorted
    @GetMapping
    public ResponseEntity<List<AstronautResponseDto>> getAllAstronauts(
            @RequestParam(defaultValue = "id") String sortBy) {
        List<AstronautResponseDto> astronauts = astronautService.getAllAstronautsSorted(sortBy);
        return ResponseEntity.ok(astronauts);
    }

    // ✅ Update by ID
    @PutMapping("/{id}")
    public ResponseEntity<AstronautResponseDto> updateAstronaut(
            @PathVariable Long id,
            @Valid @RequestBody AstronautRequestDto astronautRequestDto) {
        AstronautResponseDto updated = astronautService.updateAstronaut(id, astronautRequestDto);
        return ResponseEntity.ok(updated);
    }

    // ✅ Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAstronaut(@PathVariable Long id) {
        astronautService.deleteAstronaut(id);
        return ResponseEntity.noContent().build();
    }
}
