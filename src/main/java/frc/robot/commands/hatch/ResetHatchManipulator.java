package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Brings the hatch manipulator to its standard upright position.
 */
public class ResetHatchManipulator extends CommandGroup {

    public ResetHatchManipulator() {

        addSequential(new RetractHatchPistons());
        addSequential(new BringToStandardPositionHatch());

    } 
}
