package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.OI;
import frc.robot.commands.*;
import frc.robot.subsystems.CargoManipulator;

/**
 * Brings the cargo manipulator arm to the floor, aligns the wrist, then takes the cargo and returns to standard upright position.
 */
public class PickupCargo extends CommandGroup {


    public PickupCargo() {

        addSequential(new VibrateController(OI.manipulatorController, 0.5F));

        addSequential(new BringCargoArmToFloor());
        addSequential(new MoveCargoIntakeToAngle(CargoManipulator.RESTING_ANGLE), 2);
        addSequential(new IntakeCargo());
        addSequential(new ResetCargoManipulator());

        addSequential(new VibrateStop(OI.manipulatorController));

    } 
}
