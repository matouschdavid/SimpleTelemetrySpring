package swt6.telemetry.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swt6.telemetry.api.dtos.ApplicationDto;
import swt6.telemetry.api.dtos.ApplicationInstanceDto;
import swt6.telemetry.api.exceptions.FindByIdException;
import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.domain.ApplicationInstance;
import swt6.telemetry.jpa.logic.interfaces.ApplicationService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/application")
public class ApplicationRestController {
    private final ApplicationService applicationService;
    private final ModelMapper mapper;

    @Autowired
    public ApplicationRestController(ApplicationService applicationService, ModelMapper mapper) {
        this.applicationService = applicationService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ApplicationDto> getAll(){
        var applications = applicationService.findAll();
        var listDtoType = new TypeToken<List<ApplicationDto>>() {
        }.getType();
        return mapper.map(applications, listDtoType);
    }

    @GetMapping("{id}")
    public List<ApplicationInstanceDto> getInstancesOf(@PathVariable Long id){
        var application = applicationService.findById(id);
        if(application.isEmpty()){
            throw new FindByIdException(Application.class, id);
        }

        var instances = applicationService.findAllByApplication(application.get());
        var listDtoType = new TypeToken<List<ApplicationInstanceDto>>() {
        }.getType();
        return mapper.map(instances, listDtoType);
    }

    @PostMapping
    public void save(@RequestBody Application application){
        applicationService.save(application);
    }

    @PostMapping("{id}")
    public ApplicationInstanceDto start(@PathVariable Long id){
        var application = applicationService.findById(id);
        if(application.isEmpty()){
            throw new FindByIdException(Application.class, id);
        }

        return mapper.map(applicationService.startApplication(application.get()), ApplicationInstanceDto.class);
    }
}
