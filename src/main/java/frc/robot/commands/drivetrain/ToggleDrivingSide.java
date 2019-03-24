package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.Vision.CameraSide;

/**
 *
 */
public class ToggleDrivingSide extends InstantCommand {

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        boolean wasHatch = !Robot.drivetrain.isInverted();
        Robot.drivetrain.setInverted(!Robot.drivetrain.isInverted());

        if (wasHatch){
            Robot.vision.setPrimaryCamera(CameraSide.CARGO);
        } else {
            Robot.vision.setPrimaryCamera(CameraSide.HATCH);
        }
    }
}
