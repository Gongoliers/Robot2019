package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Enables the targeting mode on both cameras for accurate vision targeting,
 */
public class EnableTargetMode extends InstantCommand {

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.vision.disableTargetMode(Robot.frontCamera);
        Robot.vision.disableTargetMode(Robot.rearCamera);
    }

}
