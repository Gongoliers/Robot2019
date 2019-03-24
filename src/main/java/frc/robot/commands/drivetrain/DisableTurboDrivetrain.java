package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Disables the turbo mode for the drivetrain.
 */
public class DisableTurboDrivetrain extends InstantCommand {

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.drivetrain.setTurbo(false);
    }
}
