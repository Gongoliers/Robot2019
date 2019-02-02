package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Stops the cargo manipulator
 */
public class StopCargoManipulator extends InstantCommand {

    public StopCargoManipulator() {

        requires(Robot.cargoManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.cargoManipulator.stopRollers();
        Robot.cargoManipulator.stopWrist();
    }
}
