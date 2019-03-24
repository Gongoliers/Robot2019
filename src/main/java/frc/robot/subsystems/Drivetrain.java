package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.OperateDrivetrain;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.kauailabs.navx.frc.AHRS;
import com.thegongoliers.input.odometry.Odometry;
import com.thegongoliers.pathFollowing.SmartDriveTrainSubsystem;
import com.thegongoliers.pathFollowing.controllers.MotionProfileController;
import com.thegongoliers.talonsrx.GTalonSRX;
import com.thegongoliers.talonsrx.ITalonSRX;
import com.thegongoliers.talonsrx.TrajectoryCreator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The Drivetrain subsystem is composed of six motors (three on each side). Each
 * side has an encoder and a navX is used to track rotation.
 */
public class Drivetrain extends SmartDriveTrainSubsystem {

    private static final double TICKS_PER_FOOT = 208355;
    
    // Speeds
    public static final double DEFAULT_SPEED = 0.5;
    private static final double MAX_TURBO_SPEED = 0.85;
    private static final double MAX_PRECISE_SPEED = 0.55;
    private static final double MAX_PRECISE_TURN = 0.5;
    private static final double MAX_TURBO_TURN = 0.75;

    // Stabilization
    private static final double STABLE_DRIVING_CORRECTION_FACTOR = 0.04;
    private static final double STABLE_DRIVING_ROTATION_THRESHOLD = 0.15;
    public double initialGyro = 0;
    private int stopCount = 0;
    private final int MAX_COUNT = 25; // Seconds = MAX_COUNT * 20ms

    private ITalonSRX driveRight;
    private ITalonSRX driveLeft;
    private DifferentialDrive robotDrive;
    private AHRS navX;

    private boolean turbo = false;
    private boolean inverted = false;
    
    public Drivetrain() {
        driveRight = new GTalonSRX(RobotMap.rightMotor);
        driveRight.setSensor(FeedbackDevice.QuadEncoder);
        driveRight.setPID(0.01, 0, 0, (int) Math.round(0.1 * TICKS_PER_FOOT));
        driveRight.setRamp(0.05);
        driveRight.setNeutralDeadband(0.05);
        driveRight.getTalon().setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);

        GTalonSRX rightSlave1 = new GTalonSRX(RobotMap.rightSlave1);
        GTalonSRX rightSlave2 = new GTalonSRX(RobotMap.rightSlave2);

        SpeedControllerGroup rightMotors = new SpeedControllerGroup(driveRight, rightSlave1, rightSlave2);
        driveRight.setInverted(false);
        rightSlave1.setInverted(false);
        rightSlave2.setInverted(false);
        
        driveRight.setSensorPhase(true);

        driveLeft = new GTalonSRX(RobotMap.leftMotor);
        driveLeft.setSensor(FeedbackDevice.QuadEncoder);
        driveLeft.setPID(0.01, 0, 0, (int) Math.round(0.1 * TICKS_PER_FOOT));
        driveLeft.setRamp(0.05);
        driveLeft.setNeutralDeadband(0.05);
        driveLeft.getTalon().setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);

        GTalonSRX leftSlave1 = new GTalonSRX(RobotMap.leftSlave1);
        GTalonSRX leftSlave2 = new GTalonSRX(RobotMap.leftSlave2);

        SpeedControllerGroup leftMotors = new SpeedControllerGroup(driveLeft, leftSlave1, leftSlave2);
        driveLeft.setInverted(false);
        leftSlave1.setInverted(false);
        leftSlave2.setInverted(false);

        // driveLeft.setSensorPhase(true);

        robotDrive = new DifferentialDrive(leftMotors, rightMotors);

        robotDrive.setSafetyEnabled(false);
        // robotDrive.setExpiration(0.1);
        robotDrive.setMaxOutput(1.0);

        try {
            navX = new AHRS(SPI.Port.kMXP);
        } catch (Exception ex) {
            DriverStation.reportError(ex.getMessage(), true);
        }

    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());

        setDefaultCommand(new OperateDrivetrain());
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Drivetrain Distance", Odometry.getDistance(getLeftDistance(), getRightDistance()));
        SmartDashboard.putNumber("Left Distance", getLeftDistance());
        SmartDashboard.putNumber("Right Distance", getRightDistance());
        SmartDashboard.putNumber("Left Velocity", driveLeft.getVelocity() / TICKS_PER_FOOT / 10.0);
        SmartDashboard.putNumber("Right Velocity", driveRight.getVelocity() / TICKS_PER_FOOT / 10.0);
        SmartDashboard.putNumber("Drivetrain Heading", navX.getAngle());

        if (Math.abs(driveLeft.get()) > .5) {
            Robot.compressor.stop();
        } else {
            Robot.compressor.start();
        }
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
        return driveLeft.getPosition() / TICKS_PER_FOOT * Constants.FEET_TO_METERS;
    }

    public double getRightDistance() {
        return driveRight.getPosition() / TICKS_PER_FOOT * Constants.FEET_TO_METERS;
    }

    public void resetDistance() {
        driveLeft.resetEncoder();
        driveRight.resetEncoder();
    }

    /**
     * Drives to a distance in feet.
     * @param distance The distance in feet (not relative).
     */
    public void driveToDistance(double distance){
        driveLeft.setPosition(-distance * TICKS_PER_FOOT);
        driveRight.setPosition(distance * TICKS_PER_FOOT);
    }

    /**
     * Enables the usage of a joystick to move the drivetrain.
     * 
     * @param driverJoystick The joystick to be used for driving
     */
    public void operate(Joystick driverJoystick) {
        double speed = -driverJoystick.getY();
        double rotation = driverJoystick.getZ();

        double originalRotation = rotation;

        double speedMultiplier = 1;
        double rotationMultiplier = 1;

        if (inverted) {
            speedMultiplier *= -1;
        }

        if (turbo) {
            speedMultiplier *= MAX_TURBO_SPEED;
            rotationMultiplier *= MAX_TURBO_TURN;
        } else {
            speedMultiplier *= MAX_PRECISE_SPEED;
            rotationMultiplier *= MAX_PRECISE_TURN;
            // Square rotation if not in turbo mode
            rotation = Math.copySign(Math.pow(rotation, 2), rotation);
        }

        if (Math.abs(originalRotation) >= STABLE_DRIVING_ROTATION_THRESHOLD) {
			arcade(speed * speedMultiplier, rotation * rotationMultiplier);
            initialGyro = getHeading();
            stopCount = 0;
		} else {
            stopCount++;
            if (stopCount < MAX_COUNT){
                initialGyro = getHeading();
            }
            if (turbo && inverted){
                speed *= -1;
            } else if (!turbo){
                speed = speed * speedMultiplier;
            }
			arcade(speed, -STABLE_DRIVING_CORRECTION_FACTOR * (getHeading() - initialGyro));
		}
    }

    /**
     * Returns the navX angle
     */
    public double getHeading() {
        if (navX == null)
            return 0;
        return navX.getAngle();
    }

    /**
     * Resets the navX
     */
    public void resetHeading() {
        if (navX == null)
            return;
        navX.zeroYaw();
    }

    /**
     * Enables/disables turbo drivetrain mode. Turbo drivetrain mode allows movement
     * at full speed. Precise drivetrain mode contrains movement to 50% max speed.
     * 
     * @param turbo Sets the turbo mode value
     */
    public void setTurbo(boolean turbo) {
        this.turbo = turbo;
    }

    /**
     * @return Whether the drivetrain is in turbo or precise mode
     */
    public boolean isTurboEnabled() {
        return turbo;
    }

    /**
     * The hatch manipulator is on the front of the robot. The cargo manipulator is
     * on the back, therefore it is easier to control the cargo manipulator when the
     * controls are inverted.
     * 
     * @param inverted Sets the inverted value
     */
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    /**
     * @return Whether the drivetrain controls are inverted
     */
    public boolean isInverted() {
        return inverted;
    }

    /**
     * Starts a motion profile to follow an autonomous path
     * 
     * @param leftPath  an array of double arrays generated by Pathweaver for left
     *                  side wheels
     * @param rightPath an array of double arrays generated by Pathweaver for right
     *                  side wheels
     */
    public void followPath(double[][] leftPath, double[][] rightPath) {
        driveLeft.startMotionProfile(TrajectoryCreator.createTrajectory(leftPath, TICKS_PER_FOOT, TICKS_PER_FOOT * 10)); 
        driveRight.startMotionProfile(TrajectoryCreator.createTrajectory(rightPath, TICKS_PER_FOOT, TICKS_PER_FOOT * 10)); 
    }

    /**
     * Returns whether or not the motion profile autonomous path has completed its
     * journey
     * 
     * @return true if finished with path, false otherwise
     */
    public boolean isDoneFollowingPath() { 
        return driveLeft.isMotionProfileFinished() && driveRight.isMotionProfileFinished();
    }

    @Override
    public MotionProfileController getLeftDistanceController() {
        // TODO: these were too high
        return new MotionProfileController(5.0, 0, 0, 0, 0, 0.05);
    }

    @Override
    public MotionProfileController getRightDistanceController() {
        return new MotionProfileController(5.0, 0, 0, 0, 0, 0.05);
    }

    @Override
    public MotionProfileController getHeadingController() {
        return new MotionProfileController(0.04, 0.0001, 0.06, 0, 0, 1);
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

}
