package swt6.telemetry.jpa.logic.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swt6.telemetry.jpa.domain.Detector;
import swt6.telemetry.jpa.domain.Incident;
import swt6.telemetry.jpa.logic.interfaces.DetectorService;
import swt6.telemetry.jpa.repositories.DetectorRepository;
import swt6.telemetry.jpa.repositories.IncidentRepository;
import swt6.telemetry.jpa.repositories.MetricRepository;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class DetectorServiceImpl implements DetectorService {
    private final DetectorRepository detectorRepository;
    private final MetricRepository metricRepository;
    private final IncidentRepository incidentRepository;

    @Autowired
    public DetectorServiceImpl(DetectorRepository detectorRepository, MetricRepository metricRepository, IncidentRepository incidentRepository) {
        this.detectorRepository = detectorRepository;
        this.metricRepository = metricRepository;
        this.incidentRepository = incidentRepository;
    }

    @Override
    public Detector saveAndStart(Detector d) {
        d = detectorRepository.save(d);
        startDetectorLoop(d);
        return d;
    }

    private void startDetectorLoop(Detector d) {
        Thread t = new Thread(() -> {
            var detPrefix = "Detector " + d.getName();
            LocalDateTime lastCheck = LocalDateTime.now();
            while (true) {
                System.out.println(detPrefix + " is scanning for new metrics");
                var metrics = metricRepository.findMetricsByNameAndApplicationInstanceAndTimeStampBefore(d.getMetricName(), d.getInstance(), lastCheck);
                System.out.println(detPrefix + " found " + metrics.size() + " new metrics");
                if (metrics.size() > 0) {
                    List<Incident> incidents = d.process(metrics);
                    System.out.println(detPrefix + " found " + incidents.size() + " incidents");
                    if (incidents.size() > 0) {
                        incidentRepository.saveAll(incidents);
                        System.out.println(detPrefix + " saved them");
                    }
                }
                try {
                    Thread.sleep(d.getTimeInterval());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
    }

    @Override
    public void startAll() {
        detectorRepository.findAll().forEach(this::startDetectorLoop);
    }
}
