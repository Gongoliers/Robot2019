package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Follows a set path, which will be used in autonomous mode
 */

public class FollowPathDrivetrain extends Command {

    private double[][] leftPath;
    private double[][] rightPath;

    public FollowPathDrivetrain(double[][] leftPath, double[][] rightPath) {

        requires(Robot.drivetrain);

        this.leftPath = leftPath;
        this.rightPath = rightPath;

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.drivetrain.followPath(leftPath, rightPath);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.drivetrain.isDoneFollowingPath();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
