package frc.robot;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.thegongoliers.input.operator.EnhancedXboxController;
import com.thegongoliers.commands.RotateToAngle;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    /**
     * Driver Xbox controller is responsible for movement of the drivetrain.
     */
    public EnhancedXboxController driverController;
    /**
     * Manipulator Xbox controller is responsible for control of hatch/cargo/climbing subsystems.
     */
    public EnhancedXboxController manipulatorController;

    public OI() {
        /*
         * Driver
         * ------
         * LB (pressed) -> enable precise
         * RB (pressed) -> enable turbo 
         * LT (held) -> brake/reverse
         * RT (held) -> forward
         * Left Thumbstick -> Steer L/R
         * Select Button (pressed) (Small Button on top left of controller) -> Stop Everything
         * 
         * Manipulator
         * -----------
         * LB (pressed) -> Intake Cargo
         * RB (pressed) -> Pickup Cargo
         * LT (pressed) -> Deposit Cargo
         * RT (pressed) -> Deposit Hatch
         * Left Thumbstick -> Aim Cargo Wrist (Up/Down)
         * Right Thumbstick -> Aim Hatch Arm (Up/Down
         * Select Button (pressed) (Small Button on top left of controller) -> Stop Everything
         * Pause Button (held) (Small Button on top right of controller) -> Disables Climber Safety
         * PAUSE + Y (pressed) -> drop skids
         * PAUSE + B (pressed) -> retract lift piston
         * PAUSE + A (pressed) -> Lift Robot
         * UP D_PAD (held) -> Raises cargo arm
         * DOWN D_PAD (held) -> Lowers cargo arm
         */

        // Driver controller is plugged into port 0
        driverController = new EnhancedXboxController(0);

        driverController.BACK.whenPressed(new StopEverything()); // back button to emergency stop
        driverController.RB.whenPressed(new EnableTurboDrivetrain()); // RB to turbo
        driverController.LB.whenPressed(new DisableTurboDrivetrain()); // LB to precise

        // Manipulator controler is plugged into port 1
        manipulatorController = new EnhancedXboxController(1);

        manipulatorController.BACK.whenPressed(new StopEverything()); // back button to emergency stop
        manipulatorController.START.whenPressed(new DisableClimberSafety()); // allow HAB commands to work (while holding START)
        manipulatorController.START.whenReleased(new EnableClimberSafety()); // prevent HAB commands from working (when START released)

        manipulatorController.Y.whenPressed(new DeploySkids()); // press Y while holding START to deploy skids for climbing
        manipulatorController.B.whenPressed(new RetractClimber()); // press B while holding START to retract climbing piston
        manipulatorController.A.whenPressed(new ExtendClimber()); // press A while holding START to extend climbing piston
        
        manipulatorController.DPAD_DOWN.whileHeld(new LowerCargoIntake()); // DPAD-DOWN lowers cargo wrist towards floor
        manipulatorController.DPAD_UP.whileHeld(new RaiseCargoIntake()); // DPAD-UP raises cargo wrist away from floor
        
        manipulatorController.LT.whenPressed(new DepositCargo()); // LT to deposit cargo
        manipulatorController.RT.whenPressed(new DepositHatch()); // RT to deposit hatch

        manipulatorController.LB.whenPressed(new IntakeCargo()); // LB to manually intake cargo
        manipulatorController.RB.whenPressed(new PickupCargo()); // RB to automatically pickup cargo from floor
        
        // SmartDashboard Buttons
        SmartDashboard.putData("Stop Drivetrain", new StopDrivetrain());
        SmartDashboard.putData("Stop Cargo Manipulator", new StopCargoManipulator());
        SmartDashboard.putData("Stop Hatch Manipulator", new StopHatchManipulator());
        SmartDashboard.putData("** STOP EVERYTHING **", new StopEverything());
        SmartDashboard.putData("Operate Drivetrain", new OperateCargo());
        SmartDashboard.putData("Forward Drivetrain", new ForwardDrivetrain());
        SmartDashboard.putData("Backward Drivetrain", new BackwardDrivetrain());
        SmartDashboard.putData("Rotate Clockwise Drivetrain", new RotateClockwiseDrivetrain());
        SmartDashboard.putData("Rotate CounterClockwise Drivetrain", new RotateCounterClockwiseDrivetrain());
        SmartDashboard.putData("Enable Turbo Drivetrain", new EnableTurboDrivetrain());
        SmartDashboard.putData("Disable Turbo Drivetrain", new DisableTurboDrivetrain());
        SmartDashboard.putData("Follow Path Drivetrain", new FollowPathDrivetrain());
        SmartDashboard.putData("Rotate To 90° Angle Drivetrain", new RotateToAngle(Robot.drivetrain, 90));
        SmartDashboard.putData("Rotate To 180° Angle Drivetrain", new RotateToAngle(Robot.drivetrain, 180));
        SmartDashboard.putData("Bring To Floor Hatch", new BringToFloorHatch());
        SmartDashboard.putData("Bring To Standard Position Hatch", new BringToStandardPositionHatch());
        SmartDashboard.putData("Eject Hatch", new EjectHatch());
        SmartDashboard.putData("Retract Hatch Pistons", new RetractHatchPistons());
        SmartDashboard.putData("Deposit Hatch", new DepositHatch());
        SmartDashboard.putData("Reset Hatch Manipulator", new ResetHatchManipulator());
        SmartDashboard.putData("Bring Cargo Arm To Floor", new BringCargoArmToFloor());
        SmartDashboard.putData("Intake Cargo", new IntakeCargo());
        SmartDashboard.putData("Deposit Cargo", new DepositCargo());
        SmartDashboard.putData("Pickup Cargo", new PickupCargo());
        SmartDashboard.putData("Stop Cargo Intake", new StopCargoIntake());
        SmartDashboard.putData("Retract Cargo Arm", new RetractCargoArm());
        SmartDashboard.putData("Rotate Cargo Intake", new RaiseCargoIntake());
        SmartDashboard.putData("Reset Cargo Manipulator", new ResetCargoManipulator());
        SmartDashboard.putData("Deploy Skids", new DeploySkids());
        SmartDashboard.putData("Extend Climber", new ExtendClimber());
        SmartDashboard.putData("Retract Climber", new RetractClimber());

    }

}
