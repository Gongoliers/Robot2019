package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoManipulator;

/**
 * Brings the cargo manipulator arm to the floor, aligns the wrist, then takes the cargo and returns to standard upright position.
 */
public class PickupCargo extends CommandGroup {


    public PickupCargo() {

        addSequential(new MoveCargoIntakeToAngle(CargoManipulator.RESTING_ANGLE));
        addSequential(new BringCargoArmToFloor());
        addSequential(new IntakeCargo());

    } 
}
