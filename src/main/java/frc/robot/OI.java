package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.sandstorm.*;

import frc.robot.paths.returnToSideHatch.*;
import frc.robot.paths.toFrontHatch.*;
import frc.robot.paths.toPlayerStation.*;

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
         * A (pressed) -> Align to Front Target
         * B (pressed) -> Align to Rear Target
         * 
         * Manipulator
         * -----------
         * LB (pressed) -> Pickup Cargo
         * RB (pressed) -> Pickup Hatch
         * LT (pressed) -> Deposit Cargo
         * RT (pressed) -> Deposit Hatch
         * SELECT Button (pressed) (Small Button on top left of controller) -> Stop Everything
         * PAUSE Button (held) (Small Button on top right of controller) -> Disables Climber Safety
         * PAUSE + Y (pressed) -> drop skids
         * PAUSE + B (pressed) -> retract lift piston
         * PAUSE + A (pressed) -> Lift Robot
         */

        // Driver controller is plugged into port 0
        driverController = new EnhancedXboxController(0);

        driverController.BACK.whenPressed(new StopEverything()); // SELECT to stop everything
        driverController.RB.whenPressed(new EnableTurboDrivetrain()); // RB to turbo
        driverController.LB.whenPressed(new DisableTurboDrivetrain()); // LB to precise

        driverController.A.whenPressed(new AlignToFrontTarget()); // A to align front
        driverController.B.whenPressed(new AlignToRearTarget()); // B to align rear

        // Manipulator controler is plugged into port 1
        manipulatorController = new EnhancedXboxController(1);

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
        
        // SmartDashboard Buttons
        SmartDashboard.putData("Stop Drivetrain", new StopDrivetrain());
        SmartDashboard.putData("Stop Cargo Manipulator", new StopCargoManipulator());
        SmartDashboard.putData("Stop Hatch Manipulator", new StopHatchManipulator());
        SmartDashboard.putData("** STOP EVERYTHING **", new StopEverything());
        SmartDashboard.putData("Forward Drivetrain", new ForwardDrivetrain());
        SmartDashboard.putData("Backward Drivetrain", new BackwardDrivetrain());
        SmartDashboard.putData("Rotate Clockwise Drivetrain", new RotateClockwiseDrivetrain());
        SmartDashboard.putData("Rotate CounterClockwise Drivetrain", new RotateCounterClockwiseDrivetrain());
        SmartDashboard.putData("Enable Turbo Drivetrain", new EnableTurboDrivetrain());
        SmartDashboard.putData("Disable Turbo Drivetrain", new DisableTurboDrivetrain());
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
        SmartDashboard.putData("Reset Cargo Manipulator", new ResetCargoManipulator());
        SmartDashboard.putData("Deploy Skids", new DeploySkids());
        SmartDashboard.putData("Extend Climber", new ExtendClimber());
        SmartDashboard.putData("Retract Climber", new RetractClimber());

        // Vision
        SmartDashboard.putData("Vision: Align to Front Target", new AlignToFrontTarget());
        SmartDashboard.putData("Vision: Align to Rear Target", new AlignToRearTarget());
        SmartDashboard.putData("Vision: Disable Target Mode", new DisableTargetMode());
        SmartDashboard.putData("Vision: Enable Target Mode", new EnableTargetMode());

        // SmartDashboard Autonomous Command Groups
        SmartDashboard.putData("Auto: Left HAB1 Deliver Two Hatches", new AutoLeftHAB1DeliverTwoHatches());
        SmartDashboard.putData("Auto: Middle HAB1 Deliver Two Hatches", new AutoMiddleHAB1DeliverTwoHatches());
        SmartDashboard.putData("Auto: Right HAB1 Deliver Two Hatches", new AutoRightHAB1DeliverTwoHatches());

        // SmartDashboard Autonomous Paths
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
