package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Ejects the hatch from the robot
 */
public class EjectHatch extends InstantCommand {

    public EjectHatch() {

        requires(Robot.hatchManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.hatchManipulator.extend();
    }
}
