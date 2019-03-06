package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Brings the Cargo arm to the floor
 */
public class BringCargoArmToFloor extends Command {
    public BringCargoArmToFloor() {

        requires(Robot.cargoManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.cargoManipulator.extend();
        setTimeout(Robot.PISTON_TIMEOUT);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
}
