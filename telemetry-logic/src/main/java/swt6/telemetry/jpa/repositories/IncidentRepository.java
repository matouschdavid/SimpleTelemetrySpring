package swt6.telemetry.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.domain.ApplicationInstance;
import swt6.telemetry.jpa.domain.Incident;

import java.time.LocalDateTime;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    void deleteAllByDetectorInstanceAndTimeStampBefore(ApplicationInstance applicationInstance, LocalDateTime timeStamp);
    void deleteAllByDetectorInstanceApplicationAndTimeStampBefore(Application applicationInstance_application, LocalDateTime timeStamp);
    @Query("select i.detector.instance.application from Incident i group by i.detector.instance.application order by count(i) desc limit 1")
    Application findApplicationWithMostIncidents();
    @Query("select i.detector.instance from Incident i group by i.detector.instance order by count(i) desc limit 1")
    ApplicationInstance findApplicationInstanceWithMostIncidents();
}
