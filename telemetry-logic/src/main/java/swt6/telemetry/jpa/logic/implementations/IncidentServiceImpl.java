package swt6.telemetry.jpa.logic.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.domain.ApplicationInstance;
import swt6.telemetry.jpa.logic.interfaces.IncidentService;
import swt6.telemetry.jpa.repositories.IncidentRepository;

import java.time.LocalDateTime;

@Service
@Transactional
public class IncidentServiceImpl implements IncidentService {
    private final IncidentRepository repository;

    @Autowired
    public IncidentServiceImpl(IncidentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteAllTill(Application a, LocalDateTime end) {
        repository.deleteAllByDetectorInstanceApplicationAndTimeStampBefore(a, end);
    }

    @Override
    public void deleteAllTill(ApplicationInstance ai, LocalDateTime end) {
        repository.deleteAllByDetectorInstanceAndTimeStampBefore(ai, end);
    }

    @Transactional(readOnly = true)
    @Override
    public Application getApplicationWithMostIncidents() {
        return repository.findApplicationWithMostIncidents();
    }

    @Transactional(readOnly = true)
    @Override
    public ApplicationInstance getApplicationInstanceWithMostIncidents() {
        return repository.findApplicationInstanceWithMostIncidents();
    }
}
