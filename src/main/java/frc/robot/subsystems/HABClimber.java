 package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

import com.thegongoliers.output.FRCSolenoid;
import com.thegongoliers.output.Piston;
import com.thegongoliers.output.interfaces.IPiston;

/**
 *
 */
public class HABClimber extends Subsystem implements IPiston {

    /**
     * This safety value will prevent accidental activation of HAB Climber functions.
     * The skids cannot be deployed nor can the piston be controlled unless this value is set to FALSE.
     */
    private boolean safety = true;

    private IPiston climberPiston;
    private IPiston skidPiston1;
    private IPiston skidPiston2;

    /**
     * Create a HABClimber with passed in components - used for testing purposes
     */
    public HABClimber(IPiston bigPiston, IPiston piston1, IPiston piston2) {
        climberPiston = bigPiston;
        skidPiston1 = piston1;
        skidPiston2 = piston2;
    }

    public HABClimber() {
        climberPiston = new Piston(new FRCSolenoid(0, RobotMap.climberPiston)); 
        
        skidPiston1 = new Piston(new FRCSolenoid(0, RobotMap.skidPiston1));
        skidPiston1 = new Piston(new FRCSolenoid(0, RobotMap.skidPiston2));

    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    @Override
    protected void initDefaultCommand() {
        // No default command for the climber subsystem
    }

    @Override
    public void extend() {
        if (!safety)
            climberPiston.extend();
    }

    @Override
    public void retract() {
        if (!safety)
            climberPiston.retract();
    }

    @Override
    public boolean isExtended() {
        return climberPiston.isExtended();
    }

    @Override
    public boolean isRetracted() {
        return climberPiston.isRetracted();
    }

    @Override
    public void setInverted(boolean inverted) {
        climberPiston.setInverted(inverted);
    }

    @Override
    public boolean isInverted() {
        return climberPiston.isInverted();
    }

    public void deploySkids() {
        if (!safety) {
            skidPiston1.extend();
            skidPiston2.extend();
        }
    }

    /**
     * None of the climber actions will function unless climber safety mode is turned OFF (set to FALSE)
     * The skids will not lower and the pistons will not function unless safety mode is false.
     * @param safety Whether safety mode will be enabled or disabled (TRUE means HAB Climber functions DISABLED)
     */
	public void setClimberSafety(boolean safety) {
        this.safety = safety;
        if(safety) {
            Robot.oi.manipulatorController.stopVibration();
            Robot.oi.driverController.stopVibration();
        }
        else {
            Robot.oi.manipulatorController.vibrate(0.5F);
            Robot.oi.driverController.vibrate(0.2F);
        }
    }
    
    /**
     * When safety is enabled, the HAB Climber functions are prevented from accidentially activating.
     * @return Returns whether the HAB Climber functions are DISABLED
     */
    public boolean getClimberSafety() {
        return safety;
    }

}
