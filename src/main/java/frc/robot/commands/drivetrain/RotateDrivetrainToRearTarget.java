package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateDrivetrainToRearTarget extends Command {

    private double targetAngle;

    public RotateDrivetrainToRearTarget() {
        requires(Robot.drivetrain);
    }

    @Override
	protected void initialize() {
        targetAngle = Robot.drivetrain.getHeading();

        if (Robot.vision.lastFoundTarget != null) targetAngle += Robot.vision.lastFoundTarget.getHorizontalAngle() + 2;
	}

	@Override
	protected void execute() {
        double heading = Robot.drivetrain.getHeading();
        double pwm = Robot.drivetrain.getHeadingController().calculate(heading, targetAngle);// / 12.0;
        Robot.drivetrain.rotateRight(pwm);
	}

	@Override
	protected boolean isFinished() {
		return Robot.drivetrain.getHeadingController().isOnTarget(Robot.drivetrain.getHeading(), targetAngle);
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
