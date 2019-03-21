package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateDrivetrain extends Command {

    private double targetAngle;

    public RotateDrivetrain(double relativeTargetAngle) {
        requires(Robot.drivetrain);
        targetAngle = relativeTargetAngle;
    }

    @Override
	protected void initialize() {
        targetAngle += Robot.drivetrain.getHeading();
	}

	@Override
	protected void execute() {
        double heading = Robot.drivetrain.getHeading();
        double pwm = Robot.drivetrain.getHeadingController().calculate(heading, targetAngle);// / 12.0;
        Robot.drivetrain.rotateRight(pwm);
	}

	@Override
	protected boolean isFinished() {
        double heading = Robot.drivetrain.getHeading();
		return Robot.drivetrain.getHeadingController().isOnTarget(heading, targetAngle);
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
