package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.CargoManipulator;

/**
 * Lowers the roller arm to take in cargo
 */
public class LowerCargoIntake extends Command {

    public LowerCargoIntake() {

        requires(Robot.cargoManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.cargoManipulator.lowerWrist(CargoManipulator.DEFAULT_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false; // TODO: Determine when cargo arm reaches minimum height.
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.cargoManipulator.stopWrist();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
