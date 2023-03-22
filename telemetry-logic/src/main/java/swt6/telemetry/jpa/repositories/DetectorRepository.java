package swt6.telemetry.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swt6.telemetry.jpa.domain.Detector;

public interface DetectorRepository extends JpaRepository<Detector, Long> {
}
