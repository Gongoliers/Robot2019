package frc.robot;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.thegongoliers.input.operator.EnhancedXboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    /**
     * Driver Xbox controller is responsible for movement of the drivetrain.
     */
    public EnhancedXboxController driverController;
    /**
     * Manipulator Xbox controller is responsible for control of hatch/cargo/climbing subsystems.
     */
    public EnhancedXboxController manipulatorController;

    public OI() {
        // TODO: Add a "map" of the controls in the comments here: something like this
        /*
         * Driver
         * ------
         * LB (pressed) -> disable turbo
         * RB (pressed) -> enable turbo 
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
        SmartDashboard.putData("Rotate To 90° Angle Drivetrain", new RotateToAngleDrivetrain(90));
        SmartDashboard.putData("Rotate To 180° Angle Drivetrain", new RotateToAngleDrivetrain(180));
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
