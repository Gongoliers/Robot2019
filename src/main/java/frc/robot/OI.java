package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.cargo.*;
import frc.robot.commands.compressor.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.hab.*;
import frc.robot.commands.hatch.*;
import frc.robot.commands.vision.*;
import frc.robot.commands.sandstorm.*;

import frc.robot.paths.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
    public static EnhancedXboxController driverController;
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
    private JoystickButton b10;
    private JoystickButton b11;

    public OI() {
        /*
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
         * B10 (pressed) -> Enable Compressor
         * B11 (pressed) -> Disable Compressor
         */

        // Driver controller is plugged into port 0
        driverController = new EnhancedXboxController(0);

        // Manipulator controler is plugged into port 1
        manipulatorController = new EnhancedXboxController(1);

        // Manipulator joystick is plugged into port 2
        manipulatorJoystick = new Joystick(2);

        driverController.BACK.whenPressed(new StopEverything()); // SELECT to stop everything
        driverController.RB.whenPressed(new EnableTurboDrivetrain()); // RB to turbo
        driverController.LB.whenPressed(new DisableTurboDrivetrain()); // LB to precise

        driverController.A.whenPressed(new AlignToFrontTarget()); // A to align front
        driverController.B.whenPressed(new AlignToRearTarget()); // B to align rear

        driverController.DPAD_DOWN.whenPressed(new SwitchToCargoMode()); // DPAD Down for cargo mode
        driverController.DPAD_UP.whenPressed(new SwitchToHatchMode()); // DPAD Up for hatch mode

        manipulatorController.BACK.whenPressed(new StopEverything()); // SELECT to stop everything
        manipulatorController.START.whenPressed(new DisableClimberSafety()); // allow HAB commands to work (while holding PAUSE)
        manipulatorController.START.whenReleased(new EnableClimberSafety()); // prevent HAB commands from working (when PAUSE released)

        manipulatorController.Y.whenPressed(new DeploySkids()); // press Y while holding PAUSE to deploy skids for climbing
        manipulatorController.B.whenPressed(new RetractClimber()); // press B while holding PAUSE to retract climbing piston
        manipulatorController.A.whenPressed(new ExtendClimber()); // press A while holding PAUSE to extend climbing piston
        
        manipulatorController.LT.whenPressed(new DepositCargo()); // LT to deposit cargo
        manipulatorController.RT.whenPressed(new DepositHatch()); // RT to deposit hatch

        manipulatorController.LB.whenPressed(new PickupCargo()); // LB to automatically pickup cargo from floor
        manipulatorController.RB.whenPressed(new BringToFloorHatch()); // RB to automatically pickup hatch from floor

        manipulatorController.DPAD_UP.whenPressed(new StartCompressor()); // DPAD Up for compressor on 
        manipulatorController.DPAD_DOWN.whenPressed(new StopCompressor()); // DPAD Down for compressor off
        
        b1 = new JoystickButton(manipulatorJoystick, 1);
        b2 = new JoystickButton(manipulatorJoystick, 2);
        b3 = new JoystickButton(manipulatorJoystick, 3);
        b4 = new JoystickButton(manipulatorJoystick, 4);
        b5 = new JoystickButton(manipulatorJoystick, 5);
        b6 = new JoystickButton(manipulatorJoystick, 6);
        b7 = new JoystickButton(manipulatorJoystick, 7);
        b8 = new JoystickButton(manipulatorJoystick, 8);
        b10 = new JoystickButton(manipulatorJoystick, 10);
        b11 = new JoystickButton(manipulatorJoystick, 11);
        
        b1.whenPressed(new DepositHatch());
        b2.whenPressed(new DepositCargo());
        b3.whenPressed(new PickupCargo());
        b4.whenPressed(new BringToFloorHatch());
        b5.whenPressed(new StopEverything());
        b6.whenPressed(new ExtendClimber());
        b7.whenPressed(new RetractClimber());
        b8.whenPressed(new DeploySkids());
        b10.whenPressed(new StartCompressor());
        b11.whenPressed(new StopCompressor());

        // ~~ SmartDashboard Buttons ~~

        // Stops
        SmartDashboard.putData("Stop Drivetrain", new StopDrivetrain());
        SmartDashboard.putData("Stop Cargo Manipulator", new StopCargoManipulator());
        SmartDashboard.putData("Stop Hatch Manipulator", new StopHatchManipulator());
        SmartDashboard.putData("** STOP EVERYTHING **", new StopEverything());
        
        // Drivetrain
        SmartDashboard.putData("Enable Turbo Drivetrain", new EnableTurboDrivetrain());
        SmartDashboard.putData("Disable Turbo Drivetrain", new DisableTurboDrivetrain());
        SmartDashboard.putData("Rotate To 90° Angle Drivetrain", new RotateToAngle(Robot.drivetrain, 90));
        SmartDashboard.putData("Rotate To 180° Angle Drivetrain", new RotateToAngle(Robot.drivetrain, 180));
        
        // Hatch
        SmartDashboard.putData("Bring To Floor Hatch", new BringToFloorHatch());
        SmartDashboard.putData("Bring To Standard Position Hatch", new BringToStandardPositionHatch());
        SmartDashboard.putData("Eject Hatch", new EjectHatch());
        SmartDashboard.putData("Retract Hatch Pistons", new RetractHatchPistons());
        SmartDashboard.putData("Deposit Hatch", new DepositHatch());
        SmartDashboard.putData("Reset Hatch Manipulator", new ResetHatchManipulator());
        
        // Cargo
        SmartDashboard.putData("Bring Cargo Arm To Floor", new BringCargoArmToFloor());
        SmartDashboard.putData("Intake Cargo", new IntakeCargo());
        SmartDashboard.putData("Deposit Cargo", new DepositCargo());
        SmartDashboard.putData("Pickup Cargo", new PickupCargo());
        SmartDashboard.putData("Stop Cargo Intake", new StopCargoIntake());
        SmartDashboard.putData("Retract Cargo Arm", new RetractCargoArm());
        SmartDashboard.putData("Reset Cargo Manipulator", new ResetCargoManipulator());
        
        // Endgame / HAB Climber
        SmartDashboard.putData("Deploy Skids", new DeploySkids());
        SmartDashboard.putData("Extend Climber", new ExtendClimber());
        SmartDashboard.putData("Retract Climber", new RetractClimber());

        // Drivetrain controller mode
        SmartDashboard.putData("Switch to Cargo Mode", new SwitchToCargoMode());
        SmartDashboard.putData("Switch to Hatch Mode", new SwitchToHatchMode());

        // Vision
        SmartDashboard.putData("Vision: Align to Front Target", new AlignToFrontTarget());
        SmartDashboard.putData("Vision: Align to Rear Target", new AlignToRearTarget());
        SmartDashboard.putData("Vision: Disable Target Mode", new DisableTargetMode());
        SmartDashboard.putData("Vision: Enable Target Mode", new EnableTargetMode());

        // Autonomous Command Groups
        SmartDashboard.putData("Auto: Left HAB1 Deliver Two Hatches", new AutoLeftHAB1DeliverTwoHatches());
        SmartDashboard.putData("Auto: Middle HAB1 Deliver Two Hatches", new AutoMiddleHAB1DeliverTwoHatches());
        SmartDashboard.putData("Auto: Right HAB1 Deliver Two Hatches", new AutoRightHAB1DeliverTwoHatches());

        // Autonomous Paths
        SmartDashboard.putData("Path: Drive Forwards 3.6 feet", new FollowPathDrivetrain(PathDriveForwards.leftPoints, PathDriveForwards.rightPoints));

        SmartDashboard.putData("Path: Left HAB1 To FrontLeft Hatch", new FollowPathDrivetrain(PathLeftHAB1ToFrontLeftHatch.leftPoints, PathLeftHAB1ToFrontLeftHatch.rightPoints));
        SmartDashboard.putData("Path: Middle HAB1 To FrontLeft Hatch", new FollowPathDrivetrain(PathMiddleHAB1ToFrontLeftHatch.leftPoints, PathMiddleHAB1ToFrontLeftHatch.rightPoints));
        SmartDashboard.putData("Path: Right HAB1 To FrontRight Hatch", new FollowPathDrivetrain(PathRightHAB1ToFrontRightHatch.leftPoints, PathRightHAB1ToFrontRightHatch.rightPoints));

        SmartDashboard.putData("Path: Backup from Left Station", new FollowPathDrivetrain(PathBackupFromLeftStation.leftPoints, PathBackupFromLeftStation.rightPoints));
        SmartDashboard.putData("Path: Backup from Right Station", new FollowPathDrivetrain(PathBackupFromRightStation.leftPoints, PathBackupFromRightStation.rightPoints));
        SmartDashboard.putData("Path: Backup from Front Left Hatch", new FollowPathDrivetrain(PathBackupFromFrontLeftHatch.leftPoints, PathBackupFromFrontLeftHatch.rightPoints));
        SmartDashboard.putData("Path: Backup from Front Right Hatch", new FollowPathDrivetrain(PathBackupFromFrontRightHatch.leftPoints, PathBackupFromFrontRightHatch.rightPoints));

        SmartDashboard.putData("Path: To Left Station", new FollowPathDrivetrain(PathToLeftStation.leftPoints, PathToLeftStation.rightPoints));
        SmartDashboard.putData("Path: To Right Station", new FollowPathDrivetrain(PathToRightStation.leftPoints, PathToRightStation.rightPoints));
        SmartDashboard.putData("Path: To Left Side Hatch", new FollowPathDrivetrain(PathToLeftSideHatch.leftPoints, PathToLeftSideHatch.rightPoints));
        SmartDashboard.putData("Path: To Right Side Hatch", new FollowPathDrivetrain(PathToRightSideHatch.leftPoints, PathToRightSideHatch.rightPoints));

    }

}
