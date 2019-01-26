package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.thegongoliers.output.FRCSolenoid;
import com.thegongoliers.output.Piston;
import com.thegongoliers.output.interfaces.IPiston;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class HatchManipulator extends Subsystem implements IPiston {

    private Piston hatchPiston1;
    private Piston hatchPiston2;
    private WPI_TalonSRX hatchSpeedController;

    public HatchManipulator() {
        hatchPiston1 = new Piston(new FRCSolenoid(0, 3));
        
        hatchPiston2 = new Piston(new FRCSolenoid(0, 4));
        
        hatchSpeedController = new WPI_TalonSRX(6);
        hatchSpeedController.setInverted(false);

    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
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
        hatchSpeedController.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Lowers the hatch manipulator apparatus towards the floor at a specific speed
     */
    public void down(double speed) {
        hatchSpeedController.set(ControlMode.PercentOutput, -speed);
    }

}
