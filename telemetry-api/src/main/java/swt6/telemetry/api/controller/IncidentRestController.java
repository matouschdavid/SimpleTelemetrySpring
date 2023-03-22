package swt6.telemetry.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swt6.telemetry.api.exceptions.FindByIdException;
import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.domain.ApplicationInstance;
import swt6.telemetry.jpa.logic.interfaces.ApplicationInstanceService;
import swt6.telemetry.jpa.logic.interfaces.ApplicationService;
import swt6.telemetry.jpa.logic.interfaces.IncidentService;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("incident")
public class IncidentRestController {
    private final IncidentService incidentService;
    private final ApplicationService applicationService;
    private final ApplicationInstanceService applicationInstanceService;

    @Autowired
    public IncidentRestController(IncidentService incidentService, ApplicationService applicationService, ApplicationInstanceService applicationInstanceService) {
        this.incidentService = incidentService;
        this.applicationService = applicationService;
        this.applicationInstanceService = applicationInstanceService;
    }

    @GetMapping("worstApplication")
    public Application getWorstApplication(){
        return incidentService.getApplicationWithMostIncidents();
    }

    @GetMapping("worstApplicationInstance")
    public ApplicationInstance getWorstApplicationInstance(){
        return incidentService.getApplicationInstanceWithMostIncidents();
    }

    @DeleteMapping("application/{id}")
    public void deleteOfApplicationAllTill(@PathVariable Long id, @RequestParam LocalDateTime end) {
        var application = applicationService.findById(id);
        if(application.isEmpty()){
            throw new FindByIdException(Application.class, id);
        }
        incidentService.deleteAllTill(application.get(), end);
    }
    @DeleteMapping("applicationInstance/{id}")
    public void deleteOfInstanceAllTill(@PathVariable Long id, @RequestParam LocalDateTime end) {
        var applicationInstance = applicationInstanceService.findById(id);
        if(applicationInstance.isEmpty()){
            throw new FindByIdException(Application.class, id);
        }
        incidentService.deleteAllTill(applicationInstance.get(), end);
    }
}
