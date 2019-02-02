package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Disables the safety, which allows you to access features that could otherwise not be accessed
 */
public class DisableClimberSafety extends InstantCommand {

    public DisableClimberSafety() {

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.habClimber.setClimberSafety(false);
    }
}
