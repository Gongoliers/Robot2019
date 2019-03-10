package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoManipulator;

public class DepositCargo extends CommandGroup {

  public DepositCargo() {

    addSequential(new MoveCargoIntakeToAngle(CargoManipulator.MAXIMUM_ANGLE), 2);
    addSequential(new EjectCargo());

  }
}
