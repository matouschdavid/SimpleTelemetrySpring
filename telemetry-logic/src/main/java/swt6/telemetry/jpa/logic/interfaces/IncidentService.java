package swt6.telemetry.jpa.logic.interfaces;

import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.domain.ApplicationInstance;

import java.time.LocalDateTime;

public interface IncidentService {
    void deleteAllTill(Application a, LocalDateTime end);
    void deleteAllTill(ApplicationInstance ai, LocalDateTime end);
    Application getApplicationWithMostIncidents();
    ApplicationInstance getApplicationInstanceWithMostIncidents();
}
