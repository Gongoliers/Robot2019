package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.thegongoliers.output.interfaces.DriveTrainInterface;
import com.thegongoliers.talonsrx.GTalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * Runs the drivetrain subsystem
 */
public class Drivetrain extends PIDSubsystem implements DriveTrainInterface {

    public static final double DEFAULT_SPEED = 0.5;
    public static final double MAX_TURBO_SPEED = 0.9;
    public static final double MAX_NON_TURBO_SPEED = 0.5;

    private GTalonSRX driveRight;
    private GTalonSRX driveLeft;
    private DifferentialDrive robotDrive;
    private Gyro gyro; // TODO: Change type to NavX

    private boolean turbo = false;

    public Drivetrain() {
        super("", 0.02, 0, 0); // TODO: Test to find ideal values

        driveRight = new GTalonSRX(RobotMap.rightMotor);
        driveRight.setInverted(false);
        driveRight.setSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        driveRight.setPID(0.02, 0, 0, 1); // TODO: Test to find ideal values
        driveRight.setRamp(0.5); // TODO: Test to find ideal value
        driveRight.setNeutralDeadband(0.05);
        
        GTalonSRX rightSlave1 = new GTalonSRX(RobotMap.rightMotor, RobotMap.rightSlave1);
        GTalonSRX rightSlave2 = new GTalonSRX(RobotMap.rightMotor, RobotMap.rightSlave2);

        driveLeft = new GTalonSRX(RobotMap.leftMotor);
        driveLeft.setInverted(false);
        driveLeft.setSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        driveLeft.setPID(0.02, 0, 0, 1); // TODO: Test to find ideal values
        driveLeft.setRamp(0.5); // TODO: Test to find ideal value
        driveLeft.setNeutralDeadband(0.05);
        
        GTalonSRX leftSlave1 = new GTalonSRX(RobotMap.leftMotor, RobotMap.leftSlave1);
        GTalonSRX leftSlave2 = new GTalonSRX(RobotMap.leftMotor, RobotMap.leftSlave2);

        robotDrive = new DifferentialDrive(driveLeft, driveRight);
        
        robotDrive.setSafetyEnabled(true);
        robotDrive.setExpiration(0.1);
        robotDrive.setMaxOutput(1.0);

        gyro = new AnalogGyro(0); // TODO: switch to NavX
        gyro.calibrate();

    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());

        setDefaultCommand(new OperateCargo());
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

    public double getLeftDistance() {
        return driveLeft.getPosition();
    }

    public double getRightDistance() {
        return driveRight.getPosition();
    }

    public void resetDistance() {
        driveLeft.resetEncoder();
        driveRight.resetEncoder();
    }

    /**
     * Enables the usage of an Xbox controller to move the drivetrain.
     * 
     * @param driverController The Xbox controller to be used for driving
     */
	public void operate(XboxController driverController) {

        // SmartDashboard.putNumber("Encoder Distance", Odometry.getDistance(encoderLeft.getDistance(), encoderRight.getDistance()));
        // SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());

        double speed = driverController.getTriggerAxis(Hand.kRight) - driverController.getTriggerAxis(Hand.kLeft);
        double rotation = driverController.getX(Hand.kLeft);

        if (turbo) {
            robotDrive.arcadeDrive(MAX_TURBO_SPEED * speed, MAX_TURBO_SPEED * rotation);
        } else {
            robotDrive.arcadeDrive(MAX_NON_TURBO_SPEED * speed, MAX_NON_TURBO_SPEED * rotation);
        }

	}

    /**
     * Returns the gyro angle
     */
    public double getHeading() {
        return gyro.getAngle();
    }

    /**
     * Resets the gyro
     */
    public void resetHeading() {
        gyro.reset();
    }

    /**
     * Enables/disables turbo drivetrain mode.
     * Turbo drivetrain mode allows movement at full speed.
     * Precise drivetrain mode contrains movement to 50% max speed.
     * 
     * @param turbo Sets the turbo mode value
     */
    public void setTurbo(boolean turbo) {
        this.turbo = turbo;
    }

    public boolean isTurboEnabled() {
        return turbo;
    }

    @Override
    protected double returnPIDInput() {
        return 0; // TODO: Implement PID (gyro angle) 
    }

    @Override
    protected void usePIDOutput(double output) {
        // TODO: Implement PID (call rotate)
    }

    

}
