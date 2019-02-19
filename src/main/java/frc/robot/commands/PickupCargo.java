package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.CargoManipulator;

/**
 * Brings the cargo manipulator arm to the floor, aligns the wrist, then takes the cargo and returns to standard upright position.
 */
public class PickupCargo extends CommandGroup {


    public PickupCargo() {

        Robot.oi.manipulatorController.vibrate(0.2F);

        addSequential(new MoveCargoIntakeToAngle(CargoManipulator.RESTING_ANGLE));
        addSequential(new BringCargoArmToFloor());
        addSequential(new IntakeCargo());

        Robot.oi.manipulatorController.stopVibration();

    } 
}
