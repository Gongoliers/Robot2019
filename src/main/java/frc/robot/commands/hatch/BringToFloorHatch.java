package frc.robot.commands.hatch;

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
        Robot.hatchManipulator.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.hatchManipulator.setSetpoint(HatchManipulator.BOTTOM_ANGLE);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.hatchManipulator.onTarget();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.hatchManipulator.disable();
        Robot.hatchManipulator.stopArm();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
