package swt6.telemetry.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import swt6.telemetry.jpa.domain.Application;
import swt6.telemetry.jpa.domain.ApplicationInstance;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("select ai from ApplicationInstance ai where ai.application = ?1")
    List<ApplicationInstance> findAllInstances(Application application);
}
