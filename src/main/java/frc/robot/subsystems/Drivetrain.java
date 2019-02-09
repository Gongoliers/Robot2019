package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;
import com.thegongoliers.input.odometry.Odometry;
import com.thegongoliers.output.interfaces.DriveTrainInterface;
import com.thegongoliers.talonsrx.GTalonSRX;
import com.thegongoliers.talonsrx.TrajectoryCreator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * Runs the drivetrain subsystem
 */
public class Drivetrain extends PIDSubsystem implements DriveTrainInterface {

    public static final double DEFAULT_SPEED = 0.5;
    private static final double MAX_TURBO_SPEED = 0.9;
    private static final double MAX_PRECISE_SPEED = 0.5;

    private GTalonSRX driveRight;
    private GTalonSRX driveLeft;
    private DifferentialDrive robotDrive;
    private AHRS navX;

    /**
     * The number of milliseconds since the last time the hatch manipulator arm was fully raised and activating the limit switch.
     * It is possible that the limit switch could become deactivated during a bounce.
     * The drivetrain will stop if the hatch manipulator has been not fully upright for more than the number of milliseconds in DEBOUNCE_DURATION_MILLIS.
     * The drivetrain will deactivate if the hatch manipulator is down to prevent the hook tape from sticking to the carpet.
     */
    private long lastTimeAtTop = 0;
    /**
     * The drivetrain will stop if the hatch manipulator has been not fully upright for more than this number of milliseconds.
     * It is possible that the limit switch could become deactivated during a bounce.
     * The drivetrain will deactivate if the hatch manipulator is down to prevent the hook tape from sticking to the carpet.
     */
    private static final int DEBOUNCE_DURATION_MILLIS = 300;

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
        driveLeft.setInverted(true);
        driveLeft.setSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        driveLeft.setPID(0.02, 0, 0, 1); // TODO: Test to find ideal values
        driveLeft.setRamp(0.5); // TODO: Test to find ideal value
        driveLeft.setNeutralDeadband(0.05);
        
        GTalonSRX leftSlave1 = new GTalonSRX(RobotMap.leftMotor, RobotMap.leftSlave1);
        GTalonSRX leftSlave2 = new GTalonSRX(RobotMap.leftMotor, RobotMap.leftSlave2);

        leftSlave1.setInverted(true);
        leftSlave2.setInverted(true);

        robotDrive = new DifferentialDrive(driveLeft, driveRight);
        
        robotDrive.setSafetyEnabled(true);
        robotDrive.setExpiration(0.1);
        robotDrive.setMaxOutput(1.0);

        try {
            navX = new AHRS(SerialPort.Port.kMXP);
        } catch (Exception ex) {
            DriverStation.reportError(ex.getMessage(), true);
        }

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
        robotDrive.arcadeDrive(0, -speed);
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
        SmartDashboard.putNumber("Encoder Distance", Odometry.getDistance(getLeftDistance(), getRightDistance()));
        SmartDashboard.putNumber("Gyro Angle", navX.getAngle());

        if (!Robot.hatchManipulator.isAtTop() && System.currentTimeMillis() - lastTimeAtTop > DEBOUNCE_DURATION_MILLIS) {
            stop();
        }
        else {
            if (Robot.hatchManipulator.isAtTop()) {
                lastTimeAtTop = System.currentTimeMillis();
            }

            double speed = driverController.getTriggerAxis(Hand.kRight) - driverController.getTriggerAxis(Hand.kLeft);
            double rotation = driverController.getX(Hand.kLeft);

            if (turbo) {
                robotDrive.arcadeDrive(MAX_TURBO_SPEED * speed, MAX_TURBO_SPEED * rotation);
            } else {
                robotDrive.arcadeDrive(MAX_PRECISE_SPEED * speed, MAX_PRECISE_SPEED * rotation);
            }

    }
}

    /**
     * Returns the navX angle
     */
    public double getHeading() {
        if (navX == null) return 0;
        return navX.getAngle();
    }

    /**
     * Returns the navX compass angle
     */
    public double getCompassHeading() {
        if (navX == null) return 0;
        return navX.getCompassHeading();
    }

    /**
     * Resets the navX
     */
    public void resetHeading() {
        if (navX == null) return;
        navX.reset();
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
        return navX.getAngle(); // TODO: Implement PID (gyro angle) 
    }

    @Override
    protected void usePIDOutput(double output) {
        rotateRight(output);
    }

    /**
     * Starts a motion profile to follow an autonomous path
     * @param leftPath an array of double arrays generated by Pathweaver for left side wheels
     * @param rightPath an array of double arrays generated by Pathweaver for right side wheels
     */
    public void followPath(double[][] leftPath, double[][] rightPath) {
        driveLeft.startMotionProflile(TrajectoryCreator.createTrajectory(leftPath, 1, 1)); // TODO: Convert number of ticks it takes to move 1 foot
        driveRight.startMotionProflile(TrajectoryCreator.createTrajectory(rightPath, 1, 1)); // TODO: Convert number of ticks it takes to move 1 foot
    }

    /**
     * Returns whether or not the motion profile autonomous path has completed its journey
     * @return true if finished with path, false otherwise
     */
    public boolean isDoneFollowingPath() {
        return driveLeft.isMotionProfileFinished() && driveRight.isMotionProfileFinished();
    }

}
