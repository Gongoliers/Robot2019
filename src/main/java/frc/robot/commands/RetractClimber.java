package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Retracts the HAB climber piston to inside the robot.
 */
public class RetractClimber extends InstantCommand {

    public RetractClimber() {

        requires(Robot.habClimber);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.habClimber.retract();
    }
}
