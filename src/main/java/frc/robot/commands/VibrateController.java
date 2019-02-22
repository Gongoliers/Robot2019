package frc.robot.commands;

import com.thegongoliers.input.operator.EnhancedXboxController;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Vibrates a controller at a specific intensity.
 */
public class VibrateController extends InstantCommand {

    private EnhancedXboxController controller;
    private float vibration;

    public VibrateController(EnhancedXboxController controller, float vibration) {
        this.controller = controller;
        this.vibration = vibration;
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        controller.vibrate(vibration);
    }

}
