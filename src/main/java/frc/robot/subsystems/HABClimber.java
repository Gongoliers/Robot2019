package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.thegongoliers.output.FRCSolenoid;
import com.thegongoliers.output.Piston;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class HABClimber extends Subsystem {

    private Piston climberPiston;
    private Servo climberServo;

    public HABClimber() {
        climberPiston = new Piston(new FRCSolenoid(0, 2));
        
        climberServo = new Servo(5);

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

