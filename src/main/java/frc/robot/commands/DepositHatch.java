package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

/**
 * Extends and then retracts the hatch manipulator in order to deposit a possessed hatch.
 */
public class DepositHatch extends CommandGroup {


    public DepositHatch() {

        Robot.oi.manipulatorController.vibrate(0.2F);

        addSequential(new EjectHatch());
        addSequential(new RetractHatchPistons());
        
        Robot.oi.manipulatorController.stopVibration();
 
    } 
}
