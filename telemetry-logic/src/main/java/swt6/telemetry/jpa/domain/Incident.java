package swt6.telemetry.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Incident {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @ToString.Exclude
    private Detector detector;

    private LocalDateTime timeStamp;

    private double value;

    public Incident(Detector detector, LocalDateTime timeStamp, double value) {
        this.detector = detector;
        this.timeStamp = timeStamp;
        this.value = value;
    }
}
