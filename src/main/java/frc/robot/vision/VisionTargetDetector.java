package frc.robot.vision;

import java.util.List;

public interface VisionTargetDetector {

    /**
     * Detects vision targets
     * @return the targets, sorted by distance
     */
    List<VisionTarget> getTargets();

}