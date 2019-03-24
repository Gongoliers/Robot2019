package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Enables Turbo mode on the drivetrain, which allows you to accelerate to speeds that could otherwise not be accessed. This command also provides haptic feedback when used
 */
public class EnableTurboDrivetrain extends InstantCommand {

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.drivetrain.setTurbo(true);
    }
}
