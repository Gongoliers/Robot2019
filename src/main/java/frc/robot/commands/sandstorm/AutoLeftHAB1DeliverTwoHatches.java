package frc.robot.commands.sandstorm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DepositHatch;
import frc.robot.commands.FollowPathDrivetrain;
import frc.robot.paths.returnToSideHatch.PathBackupFromLeftStation;
import frc.robot.paths.returnToSideHatch.PathReturnToLeftSideHatch;
import frc.robot.paths.toFrontHatch.PathLeftHAB1ToFrontLeftHatch;
import frc.robot.paths.toPlayerStation.PathBackupFromFrontLeftHatch;
import frc.robot.paths.toPlayerStation.PathToLeftStation;

public class AutoLeftHAB1DeliverTwoHatches extends CommandGroup {
  /**
   * This sandstorm autonomous command will assume the robot is starting on the left side of the HAB level 1 platform.
   * 
   * The robot will:
   *  (1) drive and deliver one hatch to the front-left cargo bay spot,
   *  (2) pickup another hatch from the left-side player station,
   *  and (3) deliver the second hatch to the first left-side cargo bay spot.
   */
  public AutoLeftHAB1DeliverTwoHatches() {

    addSequential(new FollowPathDrivetrain(PathLeftHAB1ToFrontLeftHatch.leftPoints, PathLeftHAB1ToFrontLeftHatch.rightPoints));
    // TODO: Add some vision alignment
    addSequential(new DepositHatch());
    addSequential(new FollowPathDrivetrain(PathBackupFromFrontLeftHatch.leftPoints, PathBackupFromFrontLeftHatch.rightPoints));
    addSequential(new FollowPathDrivetrain(PathToLeftStation.leftPoints, PathToLeftStation.rightPoints));
    addSequential(new FollowPathDrivetrain(PathBackupFromLeftStation.leftPoints, PathBackupFromLeftStation.rightPoints));
    addSequential(new FollowPathDrivetrain(PathReturnToLeftSideHatch.leftPoints, PathReturnToLeftSideHatch.rightPoints));
    // TODO: Add some vision alignment
    addSequential(new DepositHatch());

  }
}
