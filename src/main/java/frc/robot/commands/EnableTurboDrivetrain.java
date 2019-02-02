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
        Robot.oi.driverController.vibrate(0.7F);
        setTimeout(0.2);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.oi.driverController.stopVibration();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
