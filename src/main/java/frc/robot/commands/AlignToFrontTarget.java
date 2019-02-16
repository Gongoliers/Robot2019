package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AlignToFrontTarget extends CommandGroup {
    /**
     * Locates a target using the front camera and then rotates the drivetrain to the target.
     */
    public AlignToFrontTarget() {
        addSequential(new LocateFrontTarget());
        addSequential(new RotateDrivetrainToFrontTarget());
    }
}
