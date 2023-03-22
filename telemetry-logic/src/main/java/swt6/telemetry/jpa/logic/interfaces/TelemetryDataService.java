package swt6.telemetry.jpa.logic.interfaces;

import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.domain.ApplicationInstance;

import java.time.LocalDateTime;

public interface TelemetryDataService {
    void generateTill(LocalDateTime end, ApplicationInstance instance, String metricName);
    void deleteAllTill(Application a, LocalDateTime end);
    void deleteAllTill(ApplicationInstance ai, LocalDateTime end);
    double getAverageValueTill(Application a, LocalDateTime end, String metricName);
    double getAverageValueTill(ApplicationInstance ai, LocalDateTime end, String metricName);

}
