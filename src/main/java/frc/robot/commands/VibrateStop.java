package frc.robot.commands;

import com.thegongoliers.input.operator.EnhancedXboxController;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Stop vibration on a specific controller.
 */
public class VibrateStop extends InstantCommand {

    private EnhancedXboxController controller;
    
    public VibrateStop(EnhancedXboxController controller) {
        this.controller = controller;
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        controller.stopVibration();
    }

}
