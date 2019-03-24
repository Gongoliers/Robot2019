package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoManipulator;

public class DepositCargo extends CommandGroup {

  public DepositCargo() {
    addSequential(new MoveCargoIntakeToAngle(CargoManipulator.shipAngle), CargoManipulator.WRIST_MOVEMENT_TIMEOUT_SECONDS);
    addSequential(new EjectCargo(CargoManipulator.CARGO_SHIP_SHOOTING_SPEED));
  }
}
