package edu.miu.cs.cs489.satcontrol.repository;

import edu.miu.cs.cs489.satcontrol.model.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SatelliteRepository extends JpaRepository<Satellite, Long> {
    Optional<Satellite> findByName(String name);
}
