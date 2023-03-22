package swt6.telemetry.jpa.logic.interfaces;

import swt6.telemetry.jpa.domain.Detector;

public interface DetectorService {
    Detector saveAndStart(Detector d);
    void startAll();
}
