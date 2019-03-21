package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.OperateDrivetrain;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.kauailabs.navx.frc.AHRS;
import com.thegongoliers.input.odometry.Odometry;
import com.thegongoliers.output.interfaces.DriveTrainInterface;
import com.thegongoliers.talonsrx.GTalonSRX;
import com.thegongoliers.talonsrx.ITalonSRX;
import com.thegongoliers.talonsrx.TrajectoryCreator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * The Drivetrain subsystem is composed of six motors (three on each side). Each
 * side has an encoder and a navX is used to track rotation.
 */
public class Drivetrain extends PIDSubsystem implements DriveTrainInterface {

    private static final double TICKS_PER_FOOT = 208355;
    public static final double DEFAULT_SPEED = 0.5;
    private static final double MAX_TURBO_SPEED = 0.85;
    private static final double MAX_PRECISE_SPEED = 0.50;
    private static final double MAX_PRECISE_TURN = 0.60;
    private static final double MAX_TURBO_TURN = 0.75;

    public double initialGyro = 0;

    private ITalonSRX driveRight;
    private ITalonSRX driveLeft;
    private DifferentialDrive robotDrive;
    private AHRS navX;

    private boolean turbo = false;
    private boolean inverted = false;

    public Drivetrain() {
        super(0.04, 0.0001, 0.06); // TODO: Test to find ideal values
        setAbsoluteTolerance(0.5);
        getPIDController().setContinuous(false);
        setOutputRange(-1, 1);

        driveRight = new GTalonSRX(RobotMap.rightMotor);
        driveRight.setSensor(FeedbackDevice.QuadEncoder);
        driveRight.setPID(0.01, 0, 0, (int) Math.round(0.25 * TICKS_PER_FOOT));
        driveRight.setRamp(0.15);
        driveRight.setNeutralDeadband(0.05);
        driveRight.getTalon().setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);

        GTalonSRX rightSlave1 = new GTalonSRX(RobotMap.rightMotor, RobotMap.rightSlave1);
        GTalonSRX rightSlave2 = new GTalonSRX(RobotMap.rightMotor, RobotMap.rightSlave2);

        driveRight.setInverted(false);
        rightSlave1.setInverted(false);
        rightSlave2.setInverted(false);
        driveRight.setSensorPhase(true);

        driveLeft = new GTalonSRX(RobotMap.leftMotor);
        driveLeft.setSensor(FeedbackDevice.QuadEncoder);
        driveLeft.setPID(0.01, 0, 0, (int) Math.round(0.25 * TICKS_PER_FOOT));
        driveLeft.setRamp(0.15);
        driveLeft.setNeutralDeadband(0.05);
        driveLeft.getTalon().setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);

        GTalonSRX leftSlave1 = new GTalonSRX(RobotMap.leftMotor, RobotMap.leftSlave1);
        GTalonSRX leftSlave2 = new GTalonSRX(RobotMap.leftMotor, RobotMap.leftSlave2);

        driveLeft.setInverted(false);
        leftSlave1.setInverted(false);
        leftSlave2.setInverted(false);

        // driveLeft.setSensorPhase(true);

        robotDrive = new DifferentialDrive(driveLeft, driveRight);

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
        SmartDashboard.putNumber("Encoder Distance", Odometry.getDistance(getLeftDistance(), getRightDistance()));
        SmartDashboard.putNumber("Gyro Angle", navX.getAngle());
        SmartDashboard.putNumber("Raw Encoder Distance", driveLeft.getPosition());
        // ticks / 100ms / ticks / foot -> feet / 100ms / 10 -> feet / sec
        SmartDashboard.putNumber("Drivetrain Speed", ((driveLeft.getVelocity() + driveRight.getVelocity()) / 2)  / TICKS_PER_FOOT / 10.0);

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
        return driveLeft.getPosition() / TICKS_PER_FOOT;
    }

    public double getRightDistance() {
        return driveRight.getPosition() / TICKS_PER_FOOT;
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
        driveLeft.setPosition(distance * TICKS_PER_FOOT);
        driveRight.setPosition(-distance * TICKS_PER_FOOT);
    }

    /**
     * Enables the usage of a joystick to move the drivetrain.
     * 
     * @param driverJoystick The joystick to be used for driving
     */
    public void operate(Joystick driverJoystick) {

        // double speed = driverController.getTriggerAxis(Hand.kRight) - driverController.getTriggerAxis(Hand.kLeft);
        double speed = -driverJoystick.getY();//driverController.getY(Hand.kLeft);
        double rotation = driverJoystick.getZ();

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
        }

        if (Math.abs(rotation) >= 0.1) {
			arcade(speed * speedMultiplier, rotation * rotationMultiplier);
			initialGyro = getHeading();
		} else {
            if (turbo && inverted){
                speed *= -1;
            } else if (!turbo){
                speed = speed * speedMultiplier;
            }
			arcade(speed, -0.01 * (getHeading() - initialGyro));
		}


        

        // speed *= 1 - (Robot.hatchManipulator.getPosition() / HatchManipulator.BOTTOM_ANGLE);

        // robotDrive.arcadeDrive(speed * speedMultiplier, rotationMultiplier);

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
     * Returns the navX compass angle
     */
    public double getCompassHeading() {
        if (navX == null)
            return 0;
        return navX.getCompassHeading();
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

}
