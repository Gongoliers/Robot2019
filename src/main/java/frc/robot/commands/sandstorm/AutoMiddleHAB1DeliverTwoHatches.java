package frc.robot.commands.sandstorm;

import com.thegongoliers.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.AlignToFrontTarget;
import frc.robot.commands.DepositHatch;
import frc.robot.commands.DisableTargetMode;
import frc.robot.commands.EnableTargetMode;
import frc.robot.commands.FollowPathDrivetrain;
import frc.robot.paths.*;

public class AutoMiddleHAB1DeliverTwoHatches extends CommandGroup {
  /**
   * This sandstorm autonomous command will assume the robot is starting on the middle of the HAB level 1 platform.
   * 
   * The robot will:
   *  (1) drive and deliver one hatch to the front-left cargo bay spot,
   *  (2) pickup another hatch from the left-side player station,
   *  and (3) deliver the second hatch to the first left-side cargo bay spot.
   */
  public AutoMiddleHAB1DeliverTwoHatches() {

    // Enable target mode
    addSequential(new EnableTargetMode());

    // Drive from HAB to cargo bay
    addSequential(new FollowPathDrivetrain(PathMiddleHAB1ToFrontLeftHatch.leftPoints, PathMiddleHAB1ToFrontLeftHatch.rightPoints));
    
    // Align and deposit hatch
    addSequential(new AlignToFrontTarget());
    addSequential(new FollowPathDrivetrain(PathDriveForwards.leftPoints, PathDriveForwards.rightPoints));
    addSequential(new DepositHatch());
    
    // Drive from cargo bay to station
    addSequential(new FollowPathDrivetrain(PathBackupFromFrontLeftHatch.leftPoints, PathBackupFromFrontLeftHatch.rightPoints));
    addSequential(new RotateToAngle(Robot.drivetrain, -90));
    addSequential(new FollowPathDrivetrain(PathToLeftStation.leftPoints, PathToLeftStation.rightPoints));
    
    // Align with station and grab hatch
    addSequential(new AlignToFrontTarget());
    addSequential(new FollowPathDrivetrain(PathDriveForwards.leftPoints, PathDriveForwards.rightPoints));

    // Drive from station to cargo bay
    addSequential(new FollowPathDrivetrain(PathBackupFromLeftStation.leftPoints, PathBackupFromLeftStation.rightPoints));
    addSequential(new RotateToAngle(Robot.drivetrain, -90));
    addSequential(new FollowPathDrivetrain(PathToLeftSideHatch.leftPoints, PathToLeftSideHatch.rightPoints));
    
    // Align and depsoit hatch
    addSequential(new AlignToFrontTarget());
    addSequential(new FollowPathDrivetrain(PathDriveForwards.leftPoints, PathDriveForwards.rightPoints));
    addSequential(new DepositHatch());

    // Disable target mode
    addSequential(new DisableTargetMode());

  }
}
