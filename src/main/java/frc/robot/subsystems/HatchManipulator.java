package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

import com.thegongoliers.output.FRCSolenoid;
import com.thegongoliers.output.Piston;
import com.thegongoliers.output.interfaces.IPiston;
import com.thegongoliers.talonsrx.GTalonSRX;
import com.thegongoliers.talonsrx.ITalonSRX;

/**
 *
 */
public class HatchManipulator extends PIDSubsystem implements IPiston {

    public static final double DEFAULT_SPEED = 0.5;
    public static final double TOP_ANGLE = 0;
    public static final double BOTTOM_ANGLE = 90; // TODO: Find this
    public static final double TOLERANCE = 10;

    private IPiston hatchPiston1;
    private IPiston hatchPiston2;
    private ITalonSRX hatchSpeedController;

    private Potentiometer hatchPotentiometer;

    public HatchManipulator() {
        super(0.02, 0, 0); // TODO: Test to find ideal PID values
        setAbsoluteTolerance(5);
        getPIDController().setContinuous(false);

        hatchPiston1 = new Piston(new FRCSolenoid(0, RobotMap.hatchPiston1));
        hatchPiston2 = new Piston(new FRCSolenoid(0, RobotMap.hatchPiston2));

        hatchSpeedController = new GTalonSRX(RobotMap.hatchMotor);
        hatchSpeedController.setInverted(false);
        hatchSpeedController.useBrakeMode();

        hatchPotentiometer = new AnalogPotentiometer(RobotMap.hatchPotentiometer, 360*10, 0);
        
    }

    /**
     * Create a HatchManipulator with passed in components - used for testing purposes
     */ 
    public HatchManipulator(IPiston piston1, IPiston piston2, ITalonSRX talon, Potentiometer potentiometer){
        super(0.02, 0, 0); // This doesn't matter
        this.hatchPiston1 = piston1;
        this.hatchPiston2 = piston2;
        this.hatchSpeedController = talon;
        this.hatchPotentiometer = potentiometer;
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ResetHatchManipulator());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    /**
     * Extends the pistons in order to eject the hatch from the manipulator
     */
    @Override
    public void extend() {
        hatchPiston1.extend();
        hatchPiston2.extend();
    }

    /**
     * Retracts the pistons to their default positions
     */
    @Override
    public void retract() {
        hatchPiston1.retract();
        hatchPiston2.retract();
    }

    /**
     * Checks if the piston is extended, and returns the result
     */
    @Override
    public boolean isExtended() {
        return hatchPiston1.isExtended();
    }

    /**
     * Checks if the piston is retracted, and returns the result
     */
                
    @Override
    public boolean isRetracted() {
        return hatchPiston1.isRetracted();
    }

    /**
     * Sets the piston's inverted state to a specific boolean value
     */
    @Override
    public void setInverted(boolean inverted) {
        hatchPiston1.setInverted(inverted);
        hatchPiston2.setInverted(inverted);
    }

    /**
    * Checks if the piston is inverted, and returns the result
    */
    @Override
    public boolean isInverted() {
        return hatchPiston1.isInverted();
    }

    /**
     * Raises the hatch manipulator apparatus towards its upright position at a specific speed 
     */
    public void up(double speed) {
        hatchSpeedController.set(speed);
    }

    /**
     * Lowers the hatch manipulator apparatus towards the floor at a specific speed
     */
    public void down(double speed) {
        hatchSpeedController.set(-speed);
    }

    public boolean isAtTop() {
        return Math.abs(hatchPotentiometer.get() - TOP_ANGLE) < TOLERANCE;
    }

    public boolean isAtBottom() { 
        return Math.abs(hatchPotentiometer.get() - BOTTOM_ANGLE) < TOLERANCE;
    }

    /**
     * Stops the manipulator arm from moving
     */
    public void stopArm(){
        hatchSpeedController.stopMotor();
    }

    @Override
    protected double returnPIDInput() {
        return hatchPotentiometer.get();
    }

    @Override
    protected void usePIDOutput(double output) {
        up(output);
    }

}
