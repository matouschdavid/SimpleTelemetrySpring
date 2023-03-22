package swt6.telemetry.jpa.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("L")

public class LogEntry extends TelemetryData {
    private LogType type;

    public LogEntry(String name, ApplicationInstance applicationInstance, LogType type) {
        super(name, applicationInstance);
        this.type = type;
    }
}
