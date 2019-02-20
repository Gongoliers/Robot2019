package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Enables the safety, which disallows you to access features that could otherwise not be accessed. 
 */
public class EnableClimberSafety extends InstantCommand {

    public EnableClimberSafety() {

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.habClimber.setClimberSafety(true);
    }
}
