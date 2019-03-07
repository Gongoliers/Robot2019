package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.HatchManipulator;

public class ManualLowerHatchula extends Command {

    public ManualLowerHatchula() {
        requires(Robot.hatchManipulator);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.hatchManipulator.down(HatchManipulator.DEFAULT_SPEED);
        if (Robot.hatchManipulator.isAtBottom()) Robot.hatchManipulator.stopArm();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
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
