package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Extends and then retracts the hatch manipulator in order to deposit a possessed hatch.
 */
public class DepositHatch extends CommandGroup {


    public DepositHatch() {

        addSequential(new EjectHatch());
        addSequential(new RetractHatchPistons());
 
    } 
}
