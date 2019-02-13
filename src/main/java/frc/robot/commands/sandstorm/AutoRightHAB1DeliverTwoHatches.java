package frc.robot.commands.sandstorm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DepositHatch;
import frc.robot.commands.FollowPathDrivetrain;
import frc.robot.paths.returnToSideHatch.PathBackupFromLeftStation;
import frc.robot.paths.returnToSideHatch.PathReturnToRightSideHatch;
import frc.robot.paths.toFrontHatch.PathRightHAB1ToFrontRightHatch;
import frc.robot.paths.toPlayerStation.PathBackupFromFrontRightHatch;
import frc.robot.paths.toPlayerStation.PathToRightStation;

public class AutoRightHAB1DeliverTwoHatches extends CommandGroup {
  /**
   * This sandstorm autonomous command will assume the robot is starting on the right side of the HAB level 1 platform.
   * 
   * The robot will:
   *  (1) drive and deliver one hatch to the front-right cargo bay spot,
   *  (2) pickup another hatch from the right-side player station,
   *  and (3) deliver the second hatch to the first right-side cargo bay spot.
   */
  public AutoRightHAB1DeliverTwoHatches() {

    addSequential(new FollowPathDrivetrain(PathRightHAB1ToFrontRightHatch.leftPoints, PathRightHAB1ToFrontRightHatch.rightPoints));
    // TODO: Add some vision alignment
    addSequential(new DepositHatch());
    addSequential(new FollowPathDrivetrain(PathBackupFromFrontRightHatch.leftPoints, PathBackupFromFrontRightHatch.rightPoints));
    addSequential(new FollowPathDrivetrain(PathToRightStation.leftPoints, PathToRightStation.rightPoints));
    addSequential(new FollowPathDrivetrain(PathBackupFromLeftStation.leftPoints, PathBackupFromLeftStation.rightPoints));
    addSequential(new FollowPathDrivetrain(PathReturnToRightSideHatch.leftPoints, PathReturnToRightSideHatch.rightPoints));
    // TODO: Add some vision alignment
    addSequential(new DepositHatch());

  }
}
