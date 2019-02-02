package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Deploys the skids for Endgame
 */
public class DeploySkids extends InstantCommand {

    public DeploySkids() {

        requires(Robot.habClimber);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.habClimber.deploySkids();
    }

}
