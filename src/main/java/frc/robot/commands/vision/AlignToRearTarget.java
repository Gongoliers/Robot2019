package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drivetrain.RotateDrivetrainToRearTarget;

public class AlignToRearTarget extends CommandGroup {
    /**
     * Locates a target using the rear camera and then rotates the drivetrain to the target.
     */
    public AlignToRearTarget() {
        addSequential(new LocateTarget());
        addSequential(new RotateDrivetrainToRearTarget());
    }
}
