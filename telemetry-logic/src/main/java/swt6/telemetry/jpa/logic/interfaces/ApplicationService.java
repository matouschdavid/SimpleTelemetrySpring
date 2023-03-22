package swt6.telemetry.jpa.logic.interfaces;

import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.domain.ApplicationInstance;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    List<Application> findAll();
    Optional<Application> findById(Long id);
    List<ApplicationInstance> findAllByApplication(Application a);
    Application save(Application a);
    ApplicationInstance startApplication(Application a);
}
