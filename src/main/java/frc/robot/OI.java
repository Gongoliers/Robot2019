package frc.robot;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.subsystems.*;


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
    public XboxController driverController;
    /**
     * Manipulator Xbox controller is responsible for control of hatch/cargo/climbing subsystems.
     */
    public XboxController manipulatorController;

    public OI() {

        // Driver controller is plugged into port 0
        driverController = new XboxController(0);
        
        // Manipulator controler is plugged into port 1                
        manipulatorController = new XboxController(1);

        // Cargo wrist controlled by DPAD
        
        
        // SmartDashboard Buttons
        SmartDashboard.putData("Stop Drivetrain", new StopDrivetrain());
        SmartDashboard.putData("Stop Cargo Manipulator", new StopCargoManipulator());
        SmartDashboard.putData("Stop Hatch Manipulator", new StopHatchManipulator());
        SmartDashboard.putData("** STOP EVERYTHING **", new StopEverything());
        SmartDashboard.putData("Operate Drivetrain", new OperateDrivetrain());
        SmartDashboard.putData("Forward Drivetrain", new ForwardDrivetrain());
        SmartDashboard.putData("Backward Drivetrain", new BackwardDrivetrain());
        SmartDashboard.putData("Rotate Clockwise Drivetrain", new RotateClockwiseDrivetrain());
        SmartDashboard.putData("Rotate CounterClockwise Drivetrain", new RotateCounterClockwiseDrivetrain());
        SmartDashboard.putData("Enable Turbo Drivetrain", new EnableTurboDrivetrain());
        SmartDashboard.putData("Disable Turbo Drivetrain", new DisableTurboDrivetrain());
        SmartDashboard.putData("Follow Path Drivetrain", new FollowPathDrivetrain());
        SmartDashboard.putData("Rotate To Angle Drivetrain", new RotateToAngleDrivetrain());
        SmartDashboard.putData("Bring To Floor Hatch", new BringToFloorHatch());
        SmartDashboard.putData("Bring To Standard Position Hatch", new BringToStandardPositionHatch());
        SmartDashboard.putData("Eject Hatch", new EjectHatch());
        SmartDashboard.putData("Retract Hatch Pistons", new RetractHatchPistons());
        SmartDashboard.putData("Deposit Hatch", new DepositHatch());
        SmartDashboard.putData("Reset Hatch Manipulator", new ResetHatchManipulator());
        SmartDashboard.putData("Bring Cargo Arm To Floor", new BringCargoArmToFloor());
        SmartDashboard.putData("Intake Cargo", new IntakeCargo());
        SmartDashboard.putData("Eject Cargo", new EjectCargo());
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
