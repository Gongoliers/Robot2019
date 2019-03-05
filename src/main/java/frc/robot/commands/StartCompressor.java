package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Starts the compressor to maintain a specific level of pressure in the pneumatic tanks.
 */
public class StartCompressor extends InstantCommand {

    // Called once when the command executes
    @Override
    protected void initialize() {

        Robot.compressor.start();

    }

}
