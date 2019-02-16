package frc.robot.commands.sandstorm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.AlignToFrontTarget;
import frc.robot.commands.DepositHatch;
import frc.robot.commands.DisableTargetMode;
import frc.robot.commands.FollowPathDrivetrain;
import frc.robot.paths.returnToSideHatch.PathBackupFromLeftStation;
import frc.robot.paths.returnToSideHatch.PathReturnToLeftSideHatch;
import frc.robot.paths.toFrontHatch.PathMiddleHAB1ToFrontLeftHatch;
import frc.robot.paths.toPlayerStation.PathBackupFromFrontLeftHatch;
import frc.robot.paths.toPlayerStation.PathToLeftStation;

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

    // Drive from HAB to cargo bay
    addSequential(new FollowPathDrivetrain(PathMiddleHAB1ToFrontLeftHatch.leftPoints, PathMiddleHAB1ToFrontLeftHatch.rightPoints));
    
    // Align and deposit hatch
    addSequential(new AlignToFrontTarget());
    addSequential(new DepositHatch());
    
    // Drive from cargo bay to station
    addSequential(new FollowPathDrivetrain(PathBackupFromFrontLeftHatch.leftPoints, PathBackupFromFrontLeftHatch.rightPoints));
    addSequential(new FollowPathDrivetrain(PathToLeftStation.leftPoints, PathToLeftStation.rightPoints));
    
    // Align with station and grab hatch
    addSequential(new AlignToFrontTarget());
    // TODO: Drive slightly forwards to "grab hatch"

    // Drive from station to cargo bay
    addSequential(new FollowPathDrivetrain(PathBackupFromLeftStation.leftPoints, PathBackupFromLeftStation.rightPoints));
    addSequential(new FollowPathDrivetrain(PathReturnToLeftSideHatch.leftPoints, PathReturnToLeftSideHatch.rightPoints));
    
    // Align and depsoit hatch
    addSequential(new AlignToFrontTarget());
    addSequential(new DepositHatch());

    // Disable target mode
    addSequential(new DisableTargetMode());

  }
}
