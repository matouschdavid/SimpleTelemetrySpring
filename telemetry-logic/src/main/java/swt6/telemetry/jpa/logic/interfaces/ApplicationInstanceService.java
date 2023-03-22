package swt6.telemetry.jpa.logic.interfaces;

import swt6.telemetry.jpa.domain.ApplicationInstance;

import java.util.Optional;

public interface ApplicationInstanceService {
    Optional<ApplicationInstance> findById(Long id);
}
