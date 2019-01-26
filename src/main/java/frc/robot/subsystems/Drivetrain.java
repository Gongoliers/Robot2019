package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Drivetrain extends Subsystem {

    private WPI_TalonSRX driveSpeedControllerRight;
    private WPI_TalonSRX driveSpeedControllerLeft;
    private RobotDrive robotDrive;

    public Drivetrain() {
        driveSpeedControllerRight = new WPI_TalonSRX(4);
        driveSpeedControllerRight.setInverted(false);
        
        driveSpeedControllerLeft = new WPI_TalonSRX(3);
        driveSpeedControllerLeft.setInverted(false);
        
        robotDrive = new RobotDrive(driveSpeedControllerLeft, driveSpeedControllerRight);
        
        robotDrive.setSafetyEnabled(true);
        robotDrive.setExpiration(0.1);
        robotDrive.setSensitivity(0.5);
        robotDrive.setMaxOutput(1.0);

        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
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

