package swt6.telemetry.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.domain.ApplicationInstance;
import swt6.telemetry.jpa.domain.Metric;

import java.time.LocalDateTime;
import java.util.List;

public interface MetricRepository extends JpaRepository<Metric, Long> {
    @Query("select avg(m.data) from Metric m where m.name = ?1 and m.applicationInstance = ?2 and m.timeStamp < ?3")
    double findAverage(String metricName, ApplicationInstance applicationInstance, LocalDateTime end);
    @Query("select avg(m.data) from Metric m where m.name = ?1 and  m.applicationInstance.application = ?2 and m.timeStamp < ?3")
    double findAverage(String metricName, Application application, LocalDateTime end);
    List<Metric> findMetricsByNameAndApplicationInstanceAndTimeStampBefore(String name, ApplicationInstance applicationInstance, LocalDateTime timeStamp);
}
