package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.CargoManipulator;

public class OperateCargoWrist extends Command {
	
	public OperateCargoWrist() {
		requires(Robot.cargoManipulator);
	}

	@Override
	protected void initialize() {
		Robot.cargoManipulator.enable();
	}

	@Override
	protected void execute() {
		double y = -OI.manipulatorController.getRightY() * CargoManipulator.MAXIMUM_ANGLE;
		y = Math.max(0, y);
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
	}

	@Override
	protected void interrupted() {
		end();
	}

}