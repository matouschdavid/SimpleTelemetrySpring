package swt6.telemetry.jpa.logic.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swt6.telemetry.jpa.domain.ApplicationInstance;
import swt6.telemetry.jpa.logic.interfaces.ApplicationInstanceService;
import swt6.telemetry.jpa.repositories.ApplicationInstanceRepository;

import java.util.Optional;

@Service
@Transactional
public class ApplicationInstanceServiceImpl implements ApplicationInstanceService {
    private final ApplicationInstanceRepository repository;

    @Autowired
    public ApplicationInstanceServiceImpl(ApplicationInstanceRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ApplicationInstance> findById(Long id) {
        return repository.findById(id);
    }
}
