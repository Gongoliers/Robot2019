package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class StopCompressor extends InstantCommand {

    // Called once when the command executes
    @Override
    protected void initialize() {

        Robot.compressor.stop();

    }

}
