package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drivetrain.RotateDrivetrainToTarget;

public class AlignToTarget extends CommandGroup {
    /**
     * Locates a target using the rear camera and then rotates the drivetrain to the target.
     */
    public AlignToTarget() {
        addSequential(new LocateTarget());
        addSequential(new RotateDrivetrainToTarget());
    }
}
