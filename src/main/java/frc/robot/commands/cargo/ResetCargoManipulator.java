package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoManipulator;

/**
 * Return the cargo manipulator to the standard upright position.
 */
public class ResetCargoManipulator extends CommandGroup {


    public ResetCargoManipulator() {

        addSequential(new StopCargoIntake());
        addSequential(new MoveCargoIntakeToAngle(CargoManipulator.RESTING_ANGLE));
        addSequential(new RetractCargoArm());
 
    } 
}
