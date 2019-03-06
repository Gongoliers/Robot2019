package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveCargoIntakeToAngle extends Command {

    private double angle;

    public MoveCargoIntakeToAngle(double angle) {
        this.angle = angle;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.cargoManipulator.enable();
        Robot.cargoManipulator.setSetpoint(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.cargoManipulator.onTarget();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.cargoManipulator.disable();
        Robot.cargoManipulator.stopWrist();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
