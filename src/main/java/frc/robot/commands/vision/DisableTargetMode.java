package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Disables the targeting mode on both cameras to return cameras to their
 * default automatic state.
 */
public class DisableTargetMode extends InstantCommand {

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.vision.disableTargetMode(Robot.vision.hatchDriverCamera);
        // Robot.vision.disableTargetMode(Robot.vision.targetingCamera);
        Robot.vision.switchToDriverCamera();
    }

}
