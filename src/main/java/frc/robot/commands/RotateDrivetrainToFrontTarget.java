package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateDrivetrainToFrontTarget extends Command {

    public RotateDrivetrainToFrontTarget() {
        requires(Robot.drivetrain);
    }

    @Override
	protected void initialize() {
        double targetAngle = 0;

        if (Robot.lastFoundTarget != null) targetAngle = Robot.lastFoundTarget.getHorizontalAngle();

		Robot.drivetrain.enable();
		Robot.drivetrain.setSetpointRelative(targetAngle);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Robot.drivetrain.onTarget();
	}

	@Override
	protected void end() {
		Robot.drivetrain.disable();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
