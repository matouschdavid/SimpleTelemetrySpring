package swt6.telemetry.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swt6.telemetry.jpa.domain.ApplicationInstance;

public interface ApplicationInstanceRepository extends JpaRepository<ApplicationInstance, Long> {
}
