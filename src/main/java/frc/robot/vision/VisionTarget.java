package frc.robot.vision;

/**
 * A FRC vision target
 */
public class VisionTarget {

    private final double horizontalAngle;
    private final double verticalAngle;
    private final double distance;

    /**
     * The default constructor.
     * @param horizontalAngle the horizontal angle in degrees
     * @param verticalAngle the vertical angle in degrees
     * @param distance the distance in units
     */
    public VisionTarget(double horizontalAngle, double verticalAngle, double distance){
        this.horizontalAngle = horizontalAngle;
        this.verticalAngle = verticalAngle;
        this.distance = distance;
    }

    /**
     * @return the horizontal angle in degrees. Negative is left of center.
     */
    public double getHorizontalAngle(){
        return horizontalAngle;
    }

    /**
     * @return the vertical angle in degrees. Negative is below center.
     */
    public double getVerticalAngle(){
        return verticalAngle;
    }

    /**
     * @return the distance to the target in units.
     */
    public double getDistance(){
        return distance;
    }

}