//package edu.miu.cs.cs489.satcontrol;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class SatcontrolApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(SatcontrolApplication.class, args);
//    }
//
//}


package edu.miu.cs.cs489.satcontrol;

import edu.miu.cs.cs489.satcontrol.model.Astronaut;
import edu.miu.cs.cs489.satcontrol.model.OrbitType;
import edu.miu.cs.cs489.satcontrol.model.Satellite;
import edu.miu.cs.cs489.satcontrol.repository.AstronautRepository;
import edu.miu.cs.cs489.satcontrol.repository.SatelliteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class SatcontrolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SatcontrolApplication.class, args);
    }

    @Bean
    CommandLineRunner run(SatelliteRepository satelliteRepository, AstronautRepository astronautRepository) {
        return args -> {

            Satellite hubble = satelliteRepository.save(
                    Satellite.builder()
                            .name("Hubble")
                            .launchDate(LocalDate.of(1990, 4, 24))
                            .orbitType(OrbitType.LEO)
                            .decommissioned(false)
                            .build()
            );

            Satellite webb = satelliteRepository.save(
                    Satellite.builder()
                            .name("James Webb")
                            .launchDate(LocalDate.of(2021, 12, 25))
                            .orbitType(OrbitType.GEO)
                            .decommissioned(false)
                            .build()
            );

            Astronaut neil = Astronaut.builder()
                    .firstName("Neil")
                    .lastName("Armstrong")
                    .experienceYears(15)
                    .satellites(Set.of(hubble))
                    .build();

            Astronaut sally = Astronaut.builder()
                    .firstName("Sally")
                    .lastName("Ride")
                    .experienceYears(12)
                    .satellites(Set.of(hubble, webb))
                    .build();

            astronautRepository.save(neil);
            astronautRepository.save(sally);
        };
    }
}

