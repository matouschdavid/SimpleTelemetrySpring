package swt6.telemetry.jpa.logic.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.domain.ApplicationInstance;
import swt6.telemetry.jpa.logic.interfaces.ApplicationService;
import swt6.telemetry.jpa.repositories.ApplicationInstanceRepository;
import swt6.telemetry.jpa.repositories.ApplicationRepository;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationInstanceRepository applicationInstanceRepository;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationInstanceRepository applicationInstanceRepository) {
        this.applicationRepository = applicationRepository;
        this.applicationInstanceRepository = applicationInstanceRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Application> findById(Long id){
        return applicationRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationInstance> findAllByApplication(Application a) {
        return applicationRepository.findAllInstances(a);
    }

    @Override
    public Application save(Application a) {
        return applicationRepository.save(a);
    }

    @Override
    public ApplicationInstance startApplication(Application a) {
        var ai = new ApplicationInstance(a);
        ai = applicationInstanceRepository.save(ai);
        return ai;
    }
}
