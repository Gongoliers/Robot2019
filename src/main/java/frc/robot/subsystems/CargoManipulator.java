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
    public static final double INTAKE_SPEED = 0.7; // TODO: Test to find ideal intake speed value.
    public static final double RESTING_ANGLE = 0;
    
    public double shootingSpeed = DEFAULT_SPEED; 

    private Piston cargoPiston1;
    private Piston cargoPiston2;

    private LimitSwitch cargoLimitSwitch;
    private Potentiometer cargoPotentiometer;

    private GTalonSRX cargoSpeedControllerWrist;
    private GTalonSRX cargoSpeedControllerRoller;

    public CargoManipulator() {
        super(0.02, 0, 0); // TODO: Test to find ideal PID values
        setAbsoluteTolerance(5);
        getPIDController().setContinuous(false);

        cargoPiston1 = new Piston(new FRCSolenoid(0, RobotMap.cargoPiston1));
        cargoPiston2 = new Piston(new FRCSolenoid(0, RobotMap.cargoPiston2));
        
        cargoLimitSwitch = new LimitSwitch(RobotMap.cargoLimitSwitch);
        cargoPotentiometer = new AnalogPotentiometer(RobotMap.cargoPotentiometer);
        
        cargoSpeedControllerWrist = new GTalonSRX(RobotMap.cargoWristMotor);
        cargoSpeedControllerWrist.setInverted(false);
        cargoSpeedControllerWrist.useBrakeMode();
        
        cargoSpeedControllerRoller = new GTalonSRX(RobotMap.cargoRollerMotor);
        cargoSpeedControllerRoller.setInverted(false);
        
    }

    /**
     * Default command of the Cargo Manipulator
     */
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ResetCargoManipulator());
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
        cargoSpeedControllerRoller.set(speed);
    }

    /**
     * Outtakes cargo by spinning both horizontal rollers at the same speed
     */
    public void outtake(double speed) {
        intake(-speed);
    }
    
    /**
     * Stops the cargo intake/outtake rollers.
     */
    public void stopRollers() {
        cargoSpeedControllerRoller.stopMotor();
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
