package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Extends the giant piston in order to lift the robot onto level 3 HAB.
 */
public class ExtendClimber extends InstantCommand {

    public ExtendClimber() {

        requires(Robot.habClimber);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.habClimber.extend();
    }
}
