package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.cargo.ResetCargoManipulator;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.thegongoliers.input.rotation.GPotentiometer;
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

    // Potentiometer properties
    public static final double POTENTIOMETER_OFFSET = 1320;
    public static final double POTENTIOMETER_SOFT_STOP_MIN = -95;
    public static final double POTENTIOMETER_SOFT_STOP_MAX = 130;
    public static final double POTENTIOMETER_NOMINAL_MIN = -100;
    public static final double POTENTIOMETER_NOMINAL_MAX = 135;

    public static final double DEFAULT_SPEED = 1;
    public static final double MAXIMUM_SPEED = 0.9;
    public static final double INTAKE_SPEED = 0.45; 
    public static final double RESTING_ANGLE = 0;
    public static final double MAXIMUM_ANGLE = 82;

    // Angles
    public static int shipAngle = 82;
    public static int rocketAngle = 45;
    public static int intakeAngle = -10;
    public static final int ANGLE_INCREMENTER = 2;
    
    // Shooting speeds
    public static final double CARGO_SHIP_SHOOTING_SPEED = 1.0;
    public static final double ROCKET_SHIP_SHOOTING_SPEED = 0.9;

    // Timeouts
    public static final double WRIST_MOVEMENT_TIMEOUT_SECONDS = 1.2; 
    public static final double EJECT_CARGO_MINIMUM_TIME = 1; 

    private IPiston cargoPiston;

    private Switch cargoLimitSwitch;
    private Potentiometer cargoPotentiometer;

    private ITalonSRX cargoSpeedControllerWrist;
    private ITalonSRX cargoSpeedControllerRoller;

    /**
     * Create a CargoManipulator with passed in components - used for testing purposes
     */
    public CargoManipulator(IPiston piston, Switch cargoSwitch, Potentiometer potentiometer, ITalonSRX wristTalon, ITalonSRX rollerTalon) {
        // FOR TESTING PURPOSES
        super(0, 0, 0);

        this.cargoPiston = piston;
        this.cargoLimitSwitch = cargoSwitch;
        this.cargoPotentiometer = potentiometer;
        this.cargoSpeedControllerRoller = rollerTalon;
        this.cargoSpeedControllerWrist = wristTalon;
    }

    public CargoManipulator() {
        // super(0.008, 0, 0);
        super(0.01, 0, 0);
        setAbsoluteTolerance(1);
        getPIDController().setContinuous(false);

        cargoPiston = new Piston(new FRCSolenoid(RobotMap.cargoPiston));
        
        cargoLimitSwitch = new LimitSwitch(RobotMap.cargoLimitSwitch).invert();
        cargoPotentiometer = new GPotentiometer(RobotMap.cargoPotentiometer, Constants.POTENTIOMETER_RANGE_DEGREES, POTENTIOMETER_OFFSET);
        
        cargoSpeedControllerWrist = new GTalonSRX(RobotMap.cargoWristMotor);
        cargoSpeedControllerWrist.setInverted(true);
        cargoSpeedControllerWrist.useBrakeMode();
        
        cargoSpeedControllerRoller = new GTalonSRX(RobotMap.cargoRollerMotor);
        cargoSpeedControllerRoller.setInverted(true);
        
    }

    /**
     * Default command of the Cargo Manipulator
     */
    @Override
    public void initDefaultCommand() {
        // setDefaultCommand(new OperateCargo());
        setDefaultCommand(new ResetCargoManipulator());
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
        SmartDashboard.putNumber("Cargo Ship Angle", shipAngle);
        SmartDashboard.putNumber("Cargo Rocket Angle", rocketAngle);
        SmartDashboard.putBoolean("Potentiometer is broken", isPotentiometerAbsolutelyDestroyed());
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

    public void increaseRocketAngle(){
        rocketAngle += ANGLE_INCREMENTER;
    }

    public void decreaseRocketAngle(){
        rocketAngle -= ANGLE_INCREMENTER;
    }

    public void increaseShipAngle(){
        shipAngle += ANGLE_INCREMENTER;
    }

    public void decreaseShipAngle(){
        shipAngle -= ANGLE_INCREMENTER;
    }

    public boolean isPotentiometerAbsolutelyDestroyed(){
        double minValue = POTENTIOMETER_NOMINAL_MIN;
        double maxValue = POTENTIOMETER_NOMINAL_MAX;
        double currentValue = returnPIDInput();
        return currentValue <= minValue || currentValue >= maxValue;
    }

}
