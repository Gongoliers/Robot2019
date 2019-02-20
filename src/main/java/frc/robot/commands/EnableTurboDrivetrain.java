package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Enables Turbo mode on the drivetrain, which allows you to accelerate to speeds that could otherwise not be accessed. This command also provides haptic feedback when used
 */
public class EnableTurboDrivetrain extends Command {

    public EnableTurboDrivetrain() {

        requires(Robot.drivetrain);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.drivetrain.setTurbo(true);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
