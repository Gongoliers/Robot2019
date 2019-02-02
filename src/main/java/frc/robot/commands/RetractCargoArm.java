package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Retracts pistons in order to raise the cargo arm.
 */
public class RetractCargoArm extends InstantCommand {

    public RetractCargoArm() {

        requires(Robot.cargoManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.cargoManipulator.retract();
    }
}
