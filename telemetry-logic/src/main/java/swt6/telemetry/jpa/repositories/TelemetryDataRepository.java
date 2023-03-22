package swt6.telemetry.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.domain.ApplicationInstance;
import swt6.telemetry.jpa.domain.TelemetryData;

import java.time.LocalDateTime;

public interface TelemetryDataRepository extends JpaRepository<TelemetryData, Long> {
    void deleteAllByApplicationInstanceAndTimeStampBefore(ApplicationInstance applicationInstance, LocalDateTime timeStamp);
    void deleteAllByApplicationInstanceApplicationAndTimeStampBefore(Application applicationInstance_application, LocalDateTime timeStamp);

}
