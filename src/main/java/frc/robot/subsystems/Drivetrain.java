package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.thegongoliers.output.interfaces.SmartDrivetrain;
import com.thegongoliers.pathFollowing.controllers.MotionProfileController;

import edu.wpi.first.wpilibj.AnalogGyro;

/**
 *
 */
public class Drivetrain extends Subsystem implements SmartDrivetrain {

    public static final double DEFAULT_SPEED = 0.5;

    private WPI_TalonSRX driveSpeedControllerRight;
    private WPI_TalonSRX driveSpeedControllerLeft;
    private DifferentialDrive robotDrive;
    private Gyro gyro;

    private boolean turbo = false;

    public Drivetrain() {
        driveSpeedControllerRight = new WPI_TalonSRX(4);
        driveSpeedControllerRight.setInverted(false);
        driveSpeedControllerRight.configOpenloopRamp(0.5); // TODO: Find ideal value
        
        driveSpeedControllerLeft = new WPI_TalonSRX(3);
        driveSpeedControllerLeft.setInverted(false);
        driveSpeedControllerLeft.configOpenloopRamp(0.5); // TODO: Find ideal value
        
        robotDrive = new DifferentialDrive(driveSpeedControllerLeft, driveSpeedControllerRight);
        
        robotDrive.setSafetyEnabled(true);
        robotDrive.setExpiration(0.1);
        robotDrive.setMaxOutput(1.0);

        gyro = new AnalogGyro(0);
        gyro.calibrate();

    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());

        setDefaultCommand(new OperateDrivetrain());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    /**
     * Drives the robot forward at a specific speed
     */
    @Override
    public void forward(double speed) {
        robotDrive.arcadeDrive(speed, 0);
    }

    /**
     * Drives the robot backward at a specific speed
     */
    @Override
    public void backward(double speed) {
        forward(-speed);
    }

    /**
     * Rotates the robot left at a specific speed
     */
    @Override
    public void rotateLeft(double speed) {
        robotDrive.arcadeDrive(0, speed);
    }

    /**
     * Rotates the robot right at a specific speed
     */
    @Override
    public void rotateRight(double speed) {
        rotateLeft(-speed);
    }

    @Override
    public void arcade(double speed, double rotation) {
        robotDrive.arcadeDrive(speed, rotation);
    }

    @Override
    public void tank(double left, double right) {
        robotDrive.tankDrive(left, right);
    }

    @Override
    public void stop() {
        robotDrive.stopMotor();
    }

    @Override
    public double getLeftDistance() {
        return 0;
    }

    @Override
    public double getRightDistance() {
        return 0;
    }

    @Override
    public void resetDistance() {

    }

    /**
     * Returns the gyro angle
     */
    @Override
    public double getHeading() {
        return gyro.getAngle();
    }

    /**
     * Resets the gyro
     */
    @Override
    public void resetHeading() {
        gyro.reset();
    }

    @Override
    public MotionProfileController getLeftDistanceController() {
        return null;
    }

    @Override
    public MotionProfileController getRightDistanceController() {
        return null;
    }

    @Override
    public MotionProfileController getHeadingController() {
        return null;
    }

    @Override
    public double getMaxVelocity() {
        return 0;
    }

    @Override
    public double getMaxAcceleration() {
        return 0;
    }

    @Override
    public double getMaxJerk() {
        return 0;
    }

    @Override
    public double getWheelbaseWidth() {
        return 0;
    }
    
    public void setTurbo(boolean turbo) {
        this.turbo = turbo;
    }

    public boolean isTurboEnabled() {
        return turbo;
    }

}

