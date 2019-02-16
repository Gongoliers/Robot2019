package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AlignToRearTarget extends CommandGroup {
    /**
     * Locates a target using the rear camera and then rotates the drivetrain to the target.
     */
    public AlignToRearTarget() {
        addSequential(new LocateRearTarget());
        addSequential(new RotateDrivetrainToRearTarget());
    }
}
