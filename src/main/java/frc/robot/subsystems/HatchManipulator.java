package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.thegongoliers.output.FRCSolenoid;
import com.thegongoliers.output.Piston;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class HatchManipulator extends Subsystem {

    private Piston hatchPiston1;
    private Piston hatchPiston2;
    private WPI_TalonSRX hatchSpeedController1;

    public HatchManipulator() {
        hatchPiston1 = new Piston(new FRCSolenoid(0, 3));
        
        hatchPiston2 = new Piston(new FRCSolenoid(0, 4));
        
        hatchSpeedController1 = new WPI_TalonSRX(6);
        hatchSpeedController1.setInverted(false);

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

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

