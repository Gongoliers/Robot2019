package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.CargoManipulator;

public class RotateCargoIntakeToRocket extends Command {


    public RotateCargoIntakeToRocket() {
        requires(Robot.cargoManipulator);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.cargoManipulator.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.cargoManipulator.setSetpoint(CargoManipulator.rocketAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
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
