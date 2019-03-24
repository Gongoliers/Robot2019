package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Ejects the hatch from the robot
 */
public class EjectHatch extends Command {

    public EjectHatch() {
        requires(Robot.hatchManipulator);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.hatchManipulator.extend();
        setTimeout(Robot.PISTON_TIMEOUT);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}
