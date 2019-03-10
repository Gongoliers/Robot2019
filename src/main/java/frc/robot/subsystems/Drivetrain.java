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
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * The Drivetrain subsystem is composed of six motors (three on each side). Each
 * side has an encoder and a navX is used to track rotation.
 */
public class Drivetrain extends PIDSubsystem implements DriveTrainInterface {

    public static final double DEFAULT_SPEED = 0.5;
    private static final double MAX_TURBO_SPEED = 0.85;
    private static final double MAX_PRECISE_SPEED = 0.50;
    private static final double MAX_PRECISE_TURN = 0.60;
    private static final double MAX_TURBO_TURN = 0.75;

    private ITalonSRX driveRight;
    private ITalonSRX driveLeft;
    private DifferentialDrive robotDrive;
    private AHRS navX;

    private boolean turbo = false;
    private boolean inverted = false;

    public Drivetrain() {
        super(0.02, 0, 0); // TODO: Test to find ideal values

        driveRight = new GTalonSRX(RobotMap.rightMotor);
        driveRight.setSensor(FeedbackDevice.QuadEncoder);
        driveRight.setPID(1.0, 0, 0, 1); // TODO: Test to find ideal values
        driveRight.setRamp(0.5);
        driveRight.setNeutralDeadband(0.05);
        driveRight.getTalon().setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);

        GTalonSRX rightSlave1 = new GTalonSRX(RobotMap.rightMotor, RobotMap.rightSlave1);
        GTalonSRX rightSlave2 = new GTalonSRX(RobotMap.rightMotor, RobotMap.rightSlave2);

        driveRight.setInverted(false);
        rightSlave1.setInverted(false);
        rightSlave2.setInverted(false);

        driveLeft = new GTalonSRX(RobotMap.leftMotor);
        driveLeft.setSensor(FeedbackDevice.QuadEncoder);
        driveLeft.setPID(1.0, 0, 0, 1); // TODO: Test to find ideal values
        driveLeft.setRamp(0.5);
        driveLeft.setNeutralDeadband(0.05);
        driveLeft.getTalon().setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);

        GTalonSRX leftSlave1 = new GTalonSRX(RobotMap.leftMotor, RobotMap.leftSlave1);
        GTalonSRX leftSlave2 = new GTalonSRX(RobotMap.leftMotor, RobotMap.leftSlave2);

        driveLeft.setInverted(false);
        leftSlave1.setInverted(false);
        leftSlave2.setInverted(false);

        // driveLeft.setSensorPhase(true);

        robotDrive = new DifferentialDrive(driveLeft, driveRight);

        robotDrive.setSafetyEnabled(true);
        robotDrive.setExpiration(0.1);
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
        SmartDashboard.putNumber("Compass Heading", navX.getCompassHeading());
        SmartDashboard.putNumber("Drivetrain Speed", (driveLeft.getVelocity() + driveRight.getVelocity()) / 2 * 13404.1287*600);

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
        return driveLeft.getPosition() / 13404.1287;
    }

    public double getRightDistance() {
        return driveRight.getPosition() / 13404.1287;
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

        // double speed = driverController.getTriggerAxis(Hand.kRight) - driverController.getTriggerAxis(Hand.kLeft);
        double speed = -driverController.getY(Hand.kLeft);
        double rotation = driverController.getX(Hand.kLeft);

        if (inverted) {
            speed *= -1;
        }

        if (turbo) {
            speed *= MAX_TURBO_SPEED;
            rotation *= MAX_TURBO_TURN;
        } else {
            speed *= MAX_PRECISE_SPEED;
            rotation *= MAX_PRECISE_TURN;
        }

        // speed *= 1 - (Robot.hatchManipulator.getPosition() / HatchManipulator.BOTTOM_ANGLE);

        robotDrive.arcadeDrive(speed, rotation);

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
        navX.reset();
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
        driveLeft.startMotionProfile(TrajectoryCreator.createTrajectory(leftPath, 1/13404.1287, 1/13404.1287/600)); 
        driveRight.startMotionProfile(TrajectoryCreator.createTrajectory(rightPath, 1/13404.1287, 1/13404.1287/600)); 
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
