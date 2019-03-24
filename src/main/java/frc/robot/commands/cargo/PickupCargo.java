package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoManipulator;

/**
 * Brings the cargo manipulator arm to the floor, aligns the wrist, then takes the cargo and returns to standard upright position.
 */
public class PickupCargo extends CommandGroup {


    public PickupCargo() {
        addSequential(new BringCargoArmToFloor());
        addSequential(new MoveCargoIntakeToAngle(CargoManipulator.intakeAngle), CargoManipulator.WRIST_MOVEMENT_TIMEOUT_SECONDS);
        addSequential(new IntakeCargo());
        addSequential(new ResetCargoManipulator());
    } 
}
