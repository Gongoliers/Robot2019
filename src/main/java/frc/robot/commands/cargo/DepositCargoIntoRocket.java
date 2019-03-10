package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoManipulator;

public class DepositCargoIntoRocket extends CommandGroup {

  public DepositCargoIntoRocket() {

    addSequential(new MoveCargoIntakeToAngle(CargoManipulator.RESTING_ANGLE + 45), 2);
    addSequential(new EjectCargo());

  }
}
