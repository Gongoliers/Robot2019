package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Stops the Hatch Manipulator
 */
public class StopHatchManipulator extends InstantCommand {

    public StopHatchManipulator() {

        requires(Robot.hatchManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.hatchManipulator.stopArm();
    }
}
