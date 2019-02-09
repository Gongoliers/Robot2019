package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

import com.thegongoliers.input.switches.LimitSwitch;
import com.thegongoliers.output.FRCSolenoid;
import com.thegongoliers.output.Piston;
import com.thegongoliers.output.interfaces.IPiston;
import com.thegongoliers.talonsrx.GTalonSRX;

/**
 *
 */
public class CargoManipulator extends PIDSubsystem implements IPiston {

    public static final double DEFAULT_SPEED = 0.5;
    public static final double MAXIMUM_SPEED = 0.9;
    public static final double RESTING_ANGLE = 0;

    private Piston cargoPiston1;
    private Piston cargoPiston2;

    private LimitSwitch cargoLimitSwitch;
    private Potentiometer cargoPotentiometer;

    private GTalonSRX cargoSpeedControllerWrist;
    private GTalonSRX cargoSpeedControllerTopRoller;
    private GTalonSRX cargoSpeedControllerBottomRoller;

    public CargoManipulator() {
        super(0.02, 0, 0); // TODO: Test to find ideal PID values
        setAbsoluteTolerance(5);
        getPIDController().setContinuous(false);

        cargoPiston1 = new Piston(new FRCSolenoid(0, RobotMap.cargoPiston1));
        cargoPiston2 = new Piston(new FRCSolenoid(0, RobotMap.cargoPiston2));
        
        cargoLimitSwitch = new LimitSwitch(RobotMap.cargoLimitSwitch);
        cargoPotentiometer = new AnalogPotentiometer(RobotMap.cargoPotentiometer);
        
        cargoSpeedControllerWrist = new GTalonSRX(RobotMap.cargoMotor1);
        cargoSpeedControllerWrist.setInverted(false);
        
        cargoSpeedControllerTopRoller = new GTalonSRX(RobotMap.cargoMotor2);
        cargoSpeedControllerTopRoller.setInverted(false);
        
        cargoSpeedControllerBottomRoller = new GTalonSRX(RobotMap.cargoMotor3);
        cargoSpeedControllerBottomRoller.setInverted(false);

    }

    /**
     * Default command of the Cargo Manipulator
     */
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new OperateCargo());
    }

    /**
     * Code that is run every loop
     */
    @Override
    public void periodic() {

    }

    /**
     * Extends the pistons to lower the cargo intake apparatus
     */ 
    @Override
    public void extend() {
        cargoPiston1.extend();
        cargoPiston2.extend();
    }

    /**
     * Retracts the pistons to raise the cargo intake apparatus
     */
    @Override
    public void retract() {
        cargoPiston1.retract();
        cargoPiston2.retract();
    }

    /**
     * Checks to see if the pistons are extended and the cargo intake apparatus is lowered
     */
    @Override
    public boolean isExtended() {
        return cargoPiston1.isExtended();
    }

    /**
     * Checks to see if the pistons are retracted and the cargo intake apparatus is raised
     */
    @Override
    public boolean isRetracted() {
        return cargoPiston2.isRetracted();
    }

    /**
     * Sets the pistons' inverted state to a boolean variable 
     */    
    @Override
    public void setInverted(boolean inverted) {
        cargoPiston1.setInverted(inverted);
        cargoPiston2.setInverted(inverted);
    }
        

    @Override
    public boolean isInverted() {
        return cargoPiston1.isInverted();
    }
    
    /**
     * Raises/aims the cargo wrist at a specific speed
     */
    public void raiseWrist(double speed) {
        cargoSpeedControllerWrist.set(speed);
    }

    /**
     * Lowers/aims the cargo wrist at a specific speed
     */
    public void lowerWrist(double speed){
      cargoSpeedControllerWrist.set(-speed);         
    }

    /**
     * Checks the limit switch to determine if the robot possesses cargo
     */
    public boolean hasCargo() {
        return cargoLimitSwitch.isTriggered();
    }

    /**
     * Intakes cargo by spinning both horizontal rollers at the same speed
     */
    public void intake(double speed) {
        cargoSpeedControllerBottomRoller.set(speed);
        cargoSpeedControllerTopRoller.set(speed);
    }

    /**
     * Outtakes cargo by spinning both horizontal rollers at the same speed
     */
    public void outtake(double speed) {
        intake(-speed);
    }
    
    /**
     * Intakes cargo by spinning both horizontal rollers at separate specific speeds
     */
    public void intake(double bottomSpeed, double topSpeed) {
        cargoSpeedControllerBottomRoller.set(bottomSpeed);
        cargoSpeedControllerTopRoller.set(topSpeed);
    }

    /**
     * Outtakes cargo by spinning both horizontal rollers at separate specific speeds
     */
    public void outtake(double bottomSpeed, double topSpeed) {
        intake(-bottomSpeed, -topSpeed);
    }

    /**
     * Stops the cargo intake/outtake rollers.
     */
    public void stopRollers() {
        cargoSpeedControllerBottomRoller.stopMotor();
        cargoSpeedControllerTopRoller.stopMotor();
    }

    /**
     * Stops the cargo wrist from moving. 
     */
    public void stopWrist(){
        cargoSpeedControllerWrist.stopMotor();
    }

	public void operate(XboxController manipulatorController) {
        cargoSpeedControllerWrist.set(manipulatorController.getY(Hand.kLeft) * MAXIMUM_SPEED);
	}     

    public void rotateWristToPosition(double position) {
        cargoSpeedControllerWrist.setPosition(position);
    }

    @Override
    protected double returnPIDInput() {
        return cargoPotentiometer.get();
    }

    @Override
    protected void usePIDOutput(double output) {
        raiseWrist(output);
    }

}
