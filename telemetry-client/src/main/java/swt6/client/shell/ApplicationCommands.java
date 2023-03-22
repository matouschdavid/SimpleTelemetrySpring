package swt6.client.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;
import swt6.jpa.domain.Application;
import swt6.jpa.domain.ApplicationInstance;

import java.util.List;

@ShellComponent
public class ApplicationCommands {
    private final String baseUrl = "http://localhost:8080/application/";
    private final RestTemplate restTemplate;

    @Autowired
    public ApplicationCommands(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @ShellMethod("Get all applications")
    public List<Application> getAll() {
        return (List<Application>) restTemplate.getForObject(baseUrl, List.class);
    }

    @ShellMethod("Get all instances of application")
    public List<ApplicationInstance> getAllOf(Application a){
        return (List<ApplicationInstance>) restTemplate.getForObject(baseUrl + a.getId(), List.class);
    }

    @ShellMethod("Save application")
    public void save(Application a){
        restTemplate.postForLocation(baseUrl, a);
    }

    @ShellMethod("Start instance")
    public ApplicationInstance startInstance(Application a){
        return restTemplate.postForObject(baseUrl + a.getId(), null, ApplicationInstance.class);
    }
}
