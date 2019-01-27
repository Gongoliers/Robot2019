package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Brings the cargo manipulator arm to the floor, aligns the wrist, then takes the cargo and returns to standard upright position.
 */
public class PickupCargo extends CommandGroup {


    public PickupCargo() {

        addSequential(new BringCargoArmToFloor());
        addSequential(new RaiseCargoIntake());
        addSequential(new IntakeCargo());
        addSequential(new ResetCargoManipulator());

    } 
}
