package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoManipulator;

public class DepositCargoIntoRocket extends CommandGroup {

  public DepositCargoIntoRocket() {
    addSequential(new MoveCargoIntakeToAngle(CargoManipulator.rocketAngle), CargoManipulator.WRIST_MOVEMENT_TIMEOUT_SECONDS);
    addSequential(new EjectCargo(CargoManipulator.ROCKET_SHIP_SHOOTING_SPEED));
  }
}
