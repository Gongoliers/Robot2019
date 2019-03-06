package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * Sets the drivetrain controls to be inverted, which is ideal for cargo
 * gameplay.
 */
public class SwitchToCargoMode extends Command {

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.drivetrain.setInverted(true);
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
