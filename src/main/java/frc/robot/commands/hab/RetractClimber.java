package frc.robot.commands.hab;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Retracts the HAB climber piston to inside the robot.
 */
public class RetractClimber extends Command {

    public RetractClimber() {

        requires(Robot.habClimber);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.habClimber.retract();
        setTimeout(Robot.PISTON_TIMEOUT);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}
