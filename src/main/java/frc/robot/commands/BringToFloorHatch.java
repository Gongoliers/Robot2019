package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.HatchManipulator;

/**
 * This command brings the hatch to floor
 */
public class BringToFloorHatch extends Command {

    public BringToFloorHatch() {

        requires(Robot.hatchManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        // TODO: Call hatch manipulator's setPosition method if not using limit switches
        Robot.hatchManipulator.down(HatchManipulator.DEFAULT_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false; // TODO: Automatically stop the hatch manipulator when it reaches desired position
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.hatchManipulator.stopArm();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
