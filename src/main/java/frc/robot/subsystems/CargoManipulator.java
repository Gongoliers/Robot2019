package frc.robot.subsystems;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.thegongoliers.input.switches.LimitSwitch;
import com.thegongoliers.output.FRCSolenoid;
import com.thegongoliers.output.Piston;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 */
public class CargoManipulator extends Subsystem {

    private Piston cargoPiston1;
    private Piston cargoPiston2;
    private LimitSwitch cargoLimitSwitch1;
    private LimitSwitch cargoLimitSwitch2;
    private LimitSwitch cargoLimitSwitch3;
    private WPI_TalonSRX cargoSpeedController1;
    private WPI_TalonSRX cargoSpeedController2;
    private WPI_TalonSRX cargoSpeedController3;

    public CargoManipulator() {
        cargoPiston1 = new Piston(new FRCSolenoid(0, 0));
        
        cargoPiston2 = new Piston(new FRCSolenoid(0, 1));
        
        cargoLimitSwitch1 = new LimitSwitch(0);
        
        cargoLimitSwitch2 = new LimitSwitch(1);
        
        cargoLimitSwitch3 = new LimitSwitch(2);
        
        cargoSpeedController1 = new WPI_TalonSRX(0);
        cargoSpeedController1.setInverted(false);
        
        cargoSpeedController2 = new WPI_TalonSRX(1);
        cargoSpeedController2.setInverted(false);
        
        cargoSpeedController3 = new WPI_TalonSRX(2);
        cargoSpeedController3.setInverted(false);

    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

