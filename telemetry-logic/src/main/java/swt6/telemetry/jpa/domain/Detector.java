package swt6.telemetry.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Detector {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    private String metricName;

    @ManyToOne
    @ToString.Exclude
    private ApplicationInstance instance;

    private Long timeInterval;
    private double minBound;
    private double maxBound;

    public Detector(String name, String metricName, ApplicationInstance instance, Long timeInterval, double minBound, double maxBound) {
        this.name = name;
        this.metricName = metricName;
        this.instance = instance;
        this.timeInterval = timeInterval;
        this.minBound = minBound;
        this.maxBound = maxBound;
    }

    public List<Incident> process(List<Metric> metrics){
        var incidents = new ArrayList<Incident>();
        for (var m : metrics) {
            if(minBound > m.getData() || m.getData() > maxBound){
                incidents.add(new Incident(this, LocalDateTime.now(), m.getData()));
            }
        }
        return incidents;
    }
}
