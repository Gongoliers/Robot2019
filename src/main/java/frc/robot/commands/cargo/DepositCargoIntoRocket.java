package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoManipulator;

public class DepositCargoIntoRocket extends CommandGroup {

  public DepositCargoIntoRocket() {

    addSequential(new MoveCargoIntakeToAngle(CargoManipulator.rocketAngle), 1.2);
    addSequential(new EjectCargo(0.9));

  }
}
