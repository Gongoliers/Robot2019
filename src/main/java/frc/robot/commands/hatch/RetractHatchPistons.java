package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracting the pistons that would expel the hatch from the manipulator.
 */
public class RetractHatchPistons extends Command {

    public RetractHatchPistons() {

        requires(Robot.hatchManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.hatchManipulator.retract();
        setTimeout(Robot.PISTON_TIMEOUT);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}
