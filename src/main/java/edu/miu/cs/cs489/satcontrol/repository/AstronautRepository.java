package edu.miu.cs.cs489.satcontrol.repository;

import edu.miu.cs.cs489.satcontrol.model.Astronaut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AstronautRepository extends JpaRepository<Astronaut, Long> {
}
