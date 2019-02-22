package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * Ejects the hatch from the robot
 */
public class EjectHatch extends Command {

    public EjectHatch() {

        requires(Robot.hatchManipulator);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        OI.manipulatorController.vibrate(0.3F);
        Robot.hatchManipulator.extend();
        setTimeout(Robot.PISTON_TIMEOUT);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    @Override
    protected void end() {
        OI.manipulatorController.stopVibration();
    }

}
