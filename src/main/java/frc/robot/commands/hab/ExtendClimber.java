package frc.robot.commands.hab;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Extends the giant piston in order to lift the robot onto level 3 HAB.
 */
public class ExtendClimber extends Command {

    public ExtendClimber() {

        requires(Robot.habClimber);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.habClimber.extend();
        setTimeout(Robot.PISTON_TIMEOUT);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}
