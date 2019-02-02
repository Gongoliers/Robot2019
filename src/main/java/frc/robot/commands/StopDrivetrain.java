package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Stops the drivetrain
 */
public class StopDrivetrain extends InstantCommand {

    public StopDrivetrain() {

        requires(Robot.drivetrain);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.drivetrain.stop();
    }
}