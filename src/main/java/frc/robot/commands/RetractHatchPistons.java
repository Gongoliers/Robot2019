package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Retracting the pistons that would expel the hatch from the manipulator.
 */
public class RetractHatchPistons extends InstantCommand {

    public RetractHatchPistons() {

        requires(Robot.hatchManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.hatchManipulator.retract();
    }
}
