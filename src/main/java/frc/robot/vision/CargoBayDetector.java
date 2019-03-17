package frc.robot.vision;

import java.util.List;


/**
 * A detector of cargo bay vision targets.
 */
public class CargoBayDetector {

    private VisionTargetDetector targetDetector;


    /**
     * Default constructor
     * @param detector the vision target detector
     */
    public CargoBayDetector(final VisionTargetDetector detector){
        this.targetDetector = detector;
    }


    /**
     * @return the vision targets
     */
	public List<VisionTarget> getTargets() {
        return targetDetector.getTargets();
    }
    
}