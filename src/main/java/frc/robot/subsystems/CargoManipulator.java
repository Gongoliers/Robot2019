package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.thegongoliers.input.switches.LimitSwitch;
import com.thegongoliers.output.FRCSolenoid;
import com.thegongoliers.output.Piston;
import com.thegongoliers.output.interfaces.IPiston;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 */
public class CargoManipulator extends Subsystem implements IPiston{

    private Piston cargoPiston1;
    private Piston cargoPiston2;
    private LimitSwitch cargoLimitSwitch1;
    private LimitSwitch cargoLimitSwitch2;
    private LimitSwitch cargoLimitSwitch3;
    private WPI_TalonSRX cargoSpeedControllerWrist;
    private WPI_TalonSRX cargoSpeedControllerTopRoller;
    private WPI_TalonSRX cargoSpeedControllerBottomRoller;

    public CargoManipulator() {
        cargoPiston1 = new Piston(new FRCSolenoid(0, 0));
        
        cargoPiston2 = new Piston(new FRCSolenoid(0, 1));
        
        cargoLimitSwitch1 = new LimitSwitch(0);
        
        cargoLimitSwitch2 = new LimitSwitch(1);
        
        cargoLimitSwitch3 = new LimitSwitch(2);
        
        cargoSpeedControllerWrist = new WPI_TalonSRX(0);
        cargoSpeedControllerWrist.setInverted(false);
        
        cargoSpeedControllerTopRoller = new WPI_TalonSRX(1);
        cargoSpeedControllerTopRoller.setInverted(false);
        
        cargoSpeedControllerBottomRoller = new WPI_TalonSRX(2);
        cargoSpeedControllerBottomRoller.setInverted(false);

    }

    /**
     * Default command of the Cargo Manipulator
     */
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new StopCargoManipulator());
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
        cargoSpeedControllerWrist.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Lowers/aims the cargo wrist at a specific speed
     */
    public void lowerWrist(double speed){
      cargoSpeedControllerWrist.set(ControlMode.PercentOutput, -speed);         
    }

    /**
     * Checks the limit switch to determine if the robot possesses cargo
     */
    public boolean hasCargo() {
        return cargoLimitSwitch1.isTriggered();
    }

    /**
     * Intakes cargo by spinning both horizontal rollers at the same speed
     */
    public void intake(double speed) {
        cargoSpeedControllerBottomRoller.set(ControlMode.PercentOutput, speed);
        cargoSpeedControllerTopRoller.set(ControlMode.PercentOutput, speed);
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
        cargoSpeedControllerBottomRoller.set(ControlMode.PercentOutput, bottomSpeed);
        cargoSpeedControllerTopRoller.set(ControlMode.PercentOutput, topSpeed);
    }

    /**
     * Outtakes cargo by spinning both horizontal rollers at separate specific speeds
     */
    public void outtake(double bottomSpeed, double topSpeed) {
        intake(-bottomSpeed, -topSpeed);
    }
}
