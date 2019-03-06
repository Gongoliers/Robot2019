package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * Sets the drivetrain controls to not inverted, which is ideal for hatch
 * gameplay.
 */
public class SwitchToHatchMode extends Command {

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.drivetrain.setInverted(false);
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
