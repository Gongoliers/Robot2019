package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * Enables Turbo mode on the drivetrain, which allows you to accelerate to speeds that could otherwise not be accessed. This command also provides haptic feedback when used
 */
public class EnableTurboDrivetrain extends Command {

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.drivetrain.setTurbo(true);
        OI.driverController.vibrate(0.6F);
        setTimeout(0.1);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        OI.driverController.stopVibration();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
