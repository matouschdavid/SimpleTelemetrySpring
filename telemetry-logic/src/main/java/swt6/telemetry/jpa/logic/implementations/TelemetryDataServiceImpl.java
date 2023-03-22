package swt6.telemetry.jpa.logic.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swt6.telemetry.jpa.domain.*;
import swt6.telemetry.jpa.logic.interfaces.TelemetryDataService;
import swt6.telemetry.jpa.repositories.MetricRepository;
import swt6.telemetry.jpa.repositories.TelemetryDataRepository;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Transactional
public class TelemetryDataServiceImpl implements TelemetryDataService {
    private final Random rnd;
    private final TelemetryDataRepository telemetryDataRepository;
    private final MetricRepository metricRepository;

    @Autowired
    public TelemetryDataServiceImpl(TelemetryDataRepository telemetryDataRepository, MetricRepository metricRepository) {
        this.telemetryDataRepository = telemetryDataRepository;
        this.metricRepository = metricRepository;
        this.rnd = new Random();
    }

    @Override
    public void generateTill(LocalDateTime end, ApplicationInstance instance, String metricName) {
        Thread t = new Thread(() -> {
            while (LocalDateTime.now().isBefore(end)) {
                boolean shouldCreateLog = rnd.nextDouble() < 0.3;
                TelemetryData data;
                if (shouldCreateLog) {
                    data = new LogEntry("Something important happened", instance, LogType.Trace);
                    System.out.println("Created a new log entry for " + instance.getApplication().getName());
                } else {
                    data = new Metric("Cpu Usage", instance, rnd.nextDouble() * 100 - 10);
                    System.out.println("Created a new metric for " + instance.getApplication().getName());
                }

                telemetryDataRepository.save(data);
                System.out.println("Saved new telemetry data");
            }
        });
        t.start();
    }

    @Override
    public void deleteAllTill(Application a, LocalDateTime end) {
        telemetryDataRepository.deleteAllByApplicationInstanceApplicationAndTimeStampBefore(a, end);
    }

    @Override
    public void deleteAllTill(ApplicationInstance ai, LocalDateTime end) {
        telemetryDataRepository.deleteAllByApplicationInstanceAndTimeStampBefore(ai, end);
    }

    @Transactional(readOnly = true)
    @Override
    public double getAverageValueTill(Application a, LocalDateTime end, String metricName) {
        return metricRepository.findAverage(metricName, a, end);
    }

    @Transactional(readOnly = true)
    @Override
    public double getAverageValueTill(ApplicationInstance ai, LocalDateTime end, String metricName) {
        return metricRepository.findAverage(metricName, ai, end);
    }
}
