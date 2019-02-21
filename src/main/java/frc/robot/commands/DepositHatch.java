package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.OI;

/**
 * Extends and then retracts the hatch manipulator in order to deposit a possessed hatch.
 */
public class DepositHatch extends CommandGroup {


    public DepositHatch() {

        OI.manipulatorController.vibrate(0.3F);

        addSequential(new EjectHatch());
        addSequential(new RetractHatchPistons());

        OI.manipulatorController.stopVibration();
        
    } 
}
