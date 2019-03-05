package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.thegongoliers.input.switches.LimitSwitch;
import com.thegongoliers.input.switches.Switch;
import com.thegongoliers.output.FRCSolenoid;
import com.thegongoliers.output.Piston;
import com.thegongoliers.output.interfaces.IPiston;
import com.thegongoliers.talonsrx.GTalonSRX;
import com.thegongoliers.talonsrx.ITalonSRX;

/**
 * The cargo manipulator is composed of two pistons that lower the "cargo arm."
 * The "cargo wrist" is controlled by a motor and another motor is used for controlling the rollers.
 */
public class CargoManipulator extends PIDSubsystem implements IPiston {

    public static final double DEFAULT_SPEED = 0.5;
    public static final double MAXIMUM_SPEED = 0.9;
    public static final double INTAKE_SPEED = 0.7; // TODO: Test to find ideal intake speed value.
    public static final double RESTING_ANGLE = 0;
    public static final double MAXIMUM_ANGLE = 11; // TODO: This is inaccurate, and is probably greater than 11 degrees in reality
    
    public double shootingSpeed = DEFAULT_SPEED; 

    private IPiston cargoPiston;

    private Switch cargoLimitSwitch;
    private Potentiometer cargoPotentiometer;

    private ITalonSRX cargoSpeedControllerWrist;
    private ITalonSRX cargoSpeedControllerRoller;

    /**
     * Create a CargoManipulator with passed in components - used for testing purposes
     */
    public CargoManipulator(IPiston piston, Switch cargoSwitch, Potentiometer potentiometer, ITalonSRX wristTalon, ITalonSRX rollerTalon) {
        super(0.02, 0, 0);

        this.cargoPiston = piston;
        this.cargoLimitSwitch = cargoSwitch;
        this.cargoPotentiometer = potentiometer;
        this.cargoSpeedControllerRoller = rollerTalon;
        this.cargoSpeedControllerWrist = wristTalon;
    }

    public CargoManipulator() {
        super(0.02, 0, 0); // TODO: Test to find ideal PID values
        setAbsoluteTolerance(5);
        getPIDController().setContinuous(false);

        cargoPiston = new Piston(new FRCSolenoid(0, RobotMap.cargoPiston));
        
        cargoLimitSwitch = new LimitSwitch(RobotMap.cargoLimitSwitch);
        cargoPotentiometer = new AnalogPotentiometer(RobotMap.cargoPotentiometer, RobotMap.POTENTIOMETER_RANGE_DEGREES, 990); // TODO: the resting angle is somewhere around 990 degrees, but this estimate can be improved in testing
        
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
        // setDefaultCommand(new ResetCargoManipulator()); TODO enable this during testing
    }

    /**
     * Code that is run every loop
     */
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Cargo Wrist Angle", cargoPotentiometer.get());
        SmartDashboard.putBoolean("Has Cargo?", cargoLimitSwitch.isTriggered());
        SmartDashboard.putBoolean("Cargo Arm Extended?", cargoPiston.isExtended());
        SmartDashboard.putNumber("Cargo Roller Speed", cargoSpeedControllerRoller.get());
    }

    /**
     * Extends the pistons to lower the cargo intake apparatus
     */ 
    @Override
    public void extend() {
        cargoPiston.extend();
    }

    /**
     * Retracts the pistons to raise the cargo intake apparatus
     */
    @Override
    public void retract() {
        cargoPiston.retract();
    }

    /**
     * Checks to see if the pistons are extended and the cargo intake apparatus is lowered
     */
    @Override
    public boolean isExtended() {
        return cargoPiston.isExtended();
    }

    /**
     * Checks to see if the pistons are retracted and the cargo intake apparatus is raised
     */
    @Override
    public boolean isRetracted() {
        return cargoPiston.isRetracted();
    }

    /**
     * Sets the pistons' inverted state to a boolean variable 
     */    
    @Override
    public void setInverted(boolean inverted) {
        cargoPiston.setInverted(inverted);
    }
        

    @Override
    public boolean isInverted() {
        return cargoPiston.isInverted();
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
