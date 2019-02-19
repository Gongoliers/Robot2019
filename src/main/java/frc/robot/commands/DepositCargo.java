package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Deposits the Cargo into the Cargo Ship
 */
public class DepositCargo extends Command {

    public DepositCargo() {

        requires(Robot.cargoManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.oi.manipulatorController.vibrate(0.2F);
        setTimeout(1);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.cargoManipulator.outtake(Robot.cargoManipulator.shootingSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isTimedOut() && !Robot.cargoManipulator.hasCargo();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.cargoManipulator.stopRollers();
        Robot.oi.manipulatorController.stopVibration();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
