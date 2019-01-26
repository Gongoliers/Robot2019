package frc.robot;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
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


    public Joystick joystick1;
    public Joystick joystick2;

    public OI() {

        joystick2 = new Joystick(1);
        
        joystick1 = new Joystick(0);
        
        // SmartDashboard Buttons
        SmartDashboard.putData("StopDrivetrain", new StopDrivetrain());
        SmartDashboard.putData("StopCargoManipulator", new StopCargoManipulator());
        SmartDashboard.putData("StopHatchManipulator", new StopHatchManipulator());
        SmartDashboard.putData("StopClimber", new StopClimber());
        SmartDashboard.putData("StopEverything", new StopEverything());
        SmartDashboard.putData("OperateDrivetrain", new OperateDrivetrain());
        SmartDashboard.putData("ForwardDrivetrain", new ForwardDrivetrain());
        SmartDashboard.putData("BackwardDrivetrain", new BackwardDrivetrain());
        SmartDashboard.putData("RotateClockwiseDrivetrain", new RotateClockwiseDrivetrain());
        SmartDashboard.putData("RotateCounterClockwiseDrivetrain", new RotateCounterClockwiseDrivetrain());
        SmartDashboard.putData("EnableTurboDrivetrain", new EnableTurboDrivetrain());
        SmartDashboard.putData("DisableTurboDrivetrain", new DisableTurboDrivetrain());
        SmartDashboard.putData("FollowPathDrivetrain", new FollowPathDrivetrain());
        SmartDashboard.putData("RotateToAngleDrivetrain", new RotateToAngleDrivetrain());
        SmartDashboard.putData("BringToFloorHatch", new BringToFloorHatch());
        SmartDashboard.putData("BringToStandardPositionHatch", new BringToStandardPositionHatch());
        SmartDashboard.putData("EjectHatch", new EjectHatch());
        SmartDashboard.putData("RetractHatchPistons", new RetractHatchPistons());
        SmartDashboard.putData("DepositHatch", new DepositHatch());
        SmartDashboard.putData("ResetHatchManipulator", new ResetHatchManipulator());
        SmartDashboard.putData("BringCargoArmToFloor", new BringCargoArmToFloor());
        SmartDashboard.putData("IntakeCargo", new IntakeCargo());
        SmartDashboard.putData("EjectCargo", new EjectCargo());
        SmartDashboard.putData("PickupCargo", new PickupCargo());
        SmartDashboard.putData("StopCargoIntake", new StopCargoIntake());
        SmartDashboard.putData("RetractCargoArm", new RetractCargoArm());
        SmartDashboard.putData("RotateCargoIntake", new RotateCargoIntake());
        SmartDashboard.putData("ResetCargoManipulator", new ResetCargoManipulator());
        SmartDashboard.putData("DeploySkids", new DeploySkids());
        SmartDashboard.putData("ExtendClimber", new ExtendClimber());
        SmartDashboard.putData("RetractClimber", new RetractClimber());

    }

    public Joystick getJoystick1() {
        return joystick1;
    }

    public Joystick getJoystick2() {
        return joystick2;
    }


}

