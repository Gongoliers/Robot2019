package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Performs an emergency stop on all subsystems.
 */
public class StopEverything extends CommandGroup {

    public StopEverything() {
        setRunWhenDisabled(true);

        addParallel(new StopCargoManipulator()); 
        addParallel(new StopDrivetrain());
        addParallel(new StopHatchManipulator());

    } 
} 
