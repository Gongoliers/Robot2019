package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Enables the targeting mode on both cameras for accurate vision targeting,
 */
public class EnableTargetMode extends Command{

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.vision.enableTargetMode(Robot.vision.frontCamera);
        Robot.vision.enableTargetMode(Robot.vision.rearCamera);
        setTimeout(1);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}
