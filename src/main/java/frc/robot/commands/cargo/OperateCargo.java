package frc.robot.commands.cargo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.CargoManipulator;

public class OperateCargo extends Command {
	
	public OperateCargo() {
		requires(Robot.cargoManipulator);
	}

	@Override
	protected void initialize() {
		Robot.cargoManipulator.enable();
	}

	@Override
	protected void execute() {
		double y = -(OI.manipulatorJoystick.getY()) * CargoManipulator.MAXIMUM_ANGLE;
		y = Math.max(0, y);
		if (y < 0.1) y = 0;
        Robot.cargoManipulator.setSetpoint(y);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
        Robot.cargoManipulator.disable();
        Robot.cargoManipulator.stopWrist();
        Robot.cargoManipulator.stopRollers();
	}

	@Override
	protected void interrupted() {
		end();
	}

}