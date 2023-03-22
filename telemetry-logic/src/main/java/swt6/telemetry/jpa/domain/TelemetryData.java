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
@ToString
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DataType", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("T")
public class TelemetryData {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime timeStamp;
    private String name;
    @ManyToOne
    private ApplicationInstance applicationInstance;

    public TelemetryData(String name, ApplicationInstance applicationInstance) {
        this.timeStamp = LocalDateTime.now();
        this.name = name;
        this.applicationInstance = applicationInstance;
    }
}
