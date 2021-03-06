package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.cargo.RetractCargoArm;
import frc.robot.commands.cargo.StopCargoManipulator;
import frc.robot.commands.drivetrain.DisableTurboDrivetrain;
import frc.robot.commands.drivetrain.StopDrivetrain;
import frc.robot.commands.hatch.StopHatchManipulator;

/**
 * Performs an emergency stop on all subsystems.
 */
public class StopEverything extends CommandGroup {

    public StopEverything() {
        setRunWhenDisabled(true);

        addParallel(new StopCargoManipulator()); 
        addParallel(new StopDrivetrain());
        addParallel(new StopHatchManipulator());
        addParallel(new DisableTurboDrivetrain());
        addSequential(new RetractCargoArm());

    } 
} 
