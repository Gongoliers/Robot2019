package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.thegongoliers.output.FRCSolenoid;
import com.thegongoliers.output.Piston;
import com.thegongoliers.output.interfaces.IPiston;
import com.thegongoliers.talonsrx.GTalonSRX;

/**
 *
 */
public class HatchManipulator extends Subsystem implements IPiston {

    public static final double DEFAULT_SPEED = 0.5;

    private Piston hatchPiston1;
    private Piston hatchPiston2;
    private GTalonSRX hatchSpeedController;

    public HatchManipulator() {
        hatchPiston1 = new Piston(new FRCSolenoid(0, 3)); // TODO: Add to robot map
        
        hatchPiston2 = new Piston(new FRCSolenoid(0, 4)); // TODO: Add to robot map
        
        hatchSpeedController = new GTalonSRX(6); // TODO: Add to robot map
        hatchSpeedController.setInverted(false);
        // TODO: Add PID and sensor if needed

    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new OperateHatch());
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

    // TODO: Add set position method if needed (not using limit switches)

    // TODO: Add is at bottom / at top methods

    /**
     * Stops the manipulator arm from moving
     */
    public void stopArm(){
        hatchSpeedController.stopMotor();
    }

	public void operate(XboxController manipulatorController) {
        // TODO
	}

}
