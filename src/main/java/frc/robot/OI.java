package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.angleIterator.DecreaseRocketAngle;
import frc.robot.commands.angleIterator.DecreaseShipAngle;
import frc.robot.commands.angleIterator.IncreaseRocketAngle;
import frc.robot.commands.angleIterator.IncreaseShipAngle;
import frc.robot.commands.cargo.*;
import frc.robot.commands.compressor.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.hatch.*;
import frc.robot.commands.vision.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.thegongoliers.input.operator.EnhancedXboxController;

import java.util.function.BooleanSupplier;

import com.thegongoliers.hardware.Hardware;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    /**
     * Driver Xbox controller is responsible for movement of the drivetrain.
     */
    public static EnhancedXboxController driverController;

    public static Joystick driverJoystick;

    /**
     * Manipulator Xbox controller is responsible for control of hatch/cargo/climbing subsystems.
     */
    public static EnhancedXboxController manipulatorController;
    /**
     * Manipulator joystick is responsible for control of hatch/cargo/climbing subsytems.
     * Designed for easy one-handed use.
     */
    public static Joystick manipulatorJoystick;

    private JoystickButton b1;
    private JoystickButton b2;
    private JoystickButton b3;
    private JoystickButton b4;
    private JoystickButton b5;
    private JoystickButton b6;
    private JoystickButton b7;
    private JoystickButton b8;
    private JoystickButton b9;
    private JoystickButton b10;
    private JoystickButton b11;

    public OI() {
        /*
         * TODO: Update this
         * Driver (Xbox)
         * -----------
         * LB (pressed) -> Enable Precise
         * RB (pressed) -> Enable Turbo 
         * LT (held) -> Brake/Reverse
         * RT (held) -> Forward
         * Left Thumbstick -> Steer L/R
         * Select Button (pressed) (Small Button on top left of controller) -> Stop Everything
         * A (pressed) -> Align to Front Target
         * B (pressed) -> Align to Rear Target
         * DPAD Down (pressed) -> Switch to Cargo Mode (invert drivetrain controls)
         * DPAD Up (pressed) -> Switch to Hatch Mode (uninvert drivetrain controls)
         * 
         * Manipulator (Xbox)
         * -----------
         * LB (pressed) -> Pickup Cargo
         * RB (pressed) -> Pickup Hatch
         * LT (pressed) -> Deposit Cargo
         * RT (pressed) -> Deposit Hatch
         * SELECT Button (pressed) (Small Button on top left of controller) -> Stop Everything
         * PAUSE Button (held) (Small Button on top right of controller) -> Disables Climber Safety
         * PAUSE + Y (pressed) -> Drop Skids
         * PAUSE + B (pressed) -> Retract Lift Piston
         * PAUSE + A (pressed) -> Lift Robot
         * X (held) -> Manually Lower Hatchula
         * DPAD Up (pressed) -> Enable Compressor
         * DPAD Down (pressed) -> Disable Compressor
         * 
         * Manipulator (Joystick)
         * -----------
         * B1 (pressed) (back trigger) -> Deposit Hatch
         * B2 (pressed) (bottom button) -> Deposit Cargo
         * B3 (pressed) (center button) -> Pickup Cargo
         * B4 (pressed) (left button) -> Pickup Hatch
         * B5 (pressed) (right button) -> Stop Everything
         * B6 (pressed) -> Lift Robot
         * B7 (pressed) -> Retract Lift Piston
         * B8 (pressed) -> Drop Skids
         * B9 (held) -> Manually Lower Hatchula
         * B10 (pressed) -> Enable Compressor
         * B11 (pressed) -> Disable Compressor
         */

        // Driver controller is plugged into port 0
        driverController = new EnhancedXboxController(0);
        driverJoystick = new Joystick(3);

        // Manipulator controler is plugged into port 1
        manipulatorController = new EnhancedXboxController(1);

        // Manipulator joystick is plugged into port 2
        manipulatorJoystick = new Joystick(2);

        // DRIVER
        Button driverTrigger = new JoystickButton(driverJoystick, 1);
        Button driverSide = new JoystickButton(driverJoystick, 2);
        Button driverTopLeft = new JoystickButton(driverJoystick, 3);
        Button driverTopRight = new JoystickButton(driverJoystick, 4);

        driverTrigger.whenPressed(new EnableTurboDrivetrain());
        driverTrigger.whenReleased(new DisableTurboDrivetrain());

        driverSide.whenPressed(new ToggleDrivingSide());
        
        Button driveStickMoved = Hardware.makeButton(new BooleanSupplier(){
        
            @Override
            public boolean getAsBoolean() {
                return Math.abs(driverJoystick.getY()) > 0.3 || Math.abs(driverJoystick.getZ()) > 0.1;
            }
        });

        driveStickMoved.whenPressed(new OperateDrivetrain());
        

        // MANIPULATOR
        b1 = new JoystickButton(manipulatorJoystick, 1);
        b2 = new JoystickButton(manipulatorJoystick, 2);
        b3 = new JoystickButton(manipulatorJoystick, 3);
        b4 = new JoystickButton(manipulatorJoystick, 4);
        b5 = new JoystickButton(manipulatorJoystick, 5);
        b6 = new JoystickButton(manipulatorJoystick, 6);
        b7 = new JoystickButton(manipulatorJoystick, 7);
        b8 = new JoystickButton(manipulatorJoystick, 8);
        b9 = new JoystickButton(manipulatorJoystick, 9);
        b10 = new JoystickButton(manipulatorJoystick, 10);
        b11 = new JoystickButton(manipulatorJoystick, 11);
        
        b1.whileHeld(new EjectHatch());
        b2.whenReleased(new RetractHatchPistons());
        b2.whenPressed(new DepositCargo());
        b3.whenPressed(new PickupCargo());
        b4.whenPressed(new DepositCargoIntoRocket());
        b5.whenPressed(new StopEverything());
        // b6.whenPressed(new RotateCargoIntakeToShip());
        // b7.whenPressed(new RotateCargoIntakeToRocket());
        // b8.whenPressed();
        b9.whenPressed(new EjectCargo());
        b10.whenPressed(new StartCompressor());
        b11.whenPressed(new StopCompressor());

        // ~~ SmartDashboard Buttons ~~
        // Drivetrain tuning
        SmartDashboard.putData("Rotate To 90° Angle Drivetrain", new RotateDrivetrain(90));
        SmartDashboard.putData("Rotate To 180° Angle Drivetrain", new RotateDrivetrain(180));
        SmartDashboard.putData("Back-up from cargo ship", new DriveForward(-1 * Constants.FEET_TO_METERS));

        // Vision
        SmartDashboard.putData("Vision: Align to Front Target", new AlignToTarget());        

        // Cargo wrist tuning
        SmartDashboard.putData("Increase Cargo Ship Angle", new IncreaseShipAngle());
        SmartDashboard.putData("Decrease Cargo Ship Angle", new DecreaseShipAngle());
        SmartDashboard.putData("Increase Cargo Rocket Angle", new IncreaseRocketAngle());
        SmartDashboard.putData("Decrease Cargo Rocket Angle", new DecreaseRocketAngle());

    }

}
