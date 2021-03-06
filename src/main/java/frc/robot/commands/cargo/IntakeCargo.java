package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.CargoManipulator;

/**
 * Starts Rollers to intake cargo
 */
public class IntakeCargo extends Command {

    public IntakeCargo() {

        requires(Robot.cargoManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.cargoManipulator.intake(CargoManipulator.INTAKE_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.cargoManipulator.hasCargo();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.cargoManipulator.stopRollers();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
