package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.CargoManipulator;

/**
 * Deposits the Cargo into the Cargo Ship
 */
public class EjectCargo extends Command {

    private double speed;

    public EjectCargo() {
        requires(Robot.cargoManipulator);
        speed = CargoManipulator.CARGO_SHIP_SHOOTING_SPEED;
    }

    public EjectCargo(double speed) {
        requires(Robot.cargoManipulator);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        setTimeout(CargoManipulator.EJECT_CARGO_MINIMUM_TIME);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.cargoManipulator.outtake(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isTimedOut() && !Robot.cargoManipulator.hasCargo();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.cargoManipulator.stopRollers();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
