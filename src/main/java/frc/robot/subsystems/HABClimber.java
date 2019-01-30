 package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.thegongoliers.output.FRCSolenoid;
import com.thegongoliers.output.Piston;
import com.thegongoliers.output.interfaces.IPiston;

/**
 *
 */
public class HABClimber extends Subsystem implements IPiston {

    private Piston climberPiston;
    private Piston skidPiston1;
    private Piston skidPiston2;

    public HABClimber() {
        climberPiston = new Piston(new FRCSolenoid(0, 2));
        
        skidPiston1 = new Piston(new FRCSolenoid(0, 5));
        skidPiston1 = new Piston(new FRCSolenoid(0, 6));

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
        climberPiston.extend();
    }

    @Override
    public void retract() {
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
        skidPiston1.extend();
        skidPiston2.extend();
    }

}
