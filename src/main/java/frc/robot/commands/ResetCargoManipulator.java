package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Return the cargo manipulator to the standard upright position.
 */
public class ResetCargoManipulator extends CommandGroup {


    public ResetCargoManipulator() {

        addSequential(new StopCargoIntake());
        addSequential(new RaiseCargoIntake());
        addSequential(new RetractCargoArm());
 
    } 
}
