package frc.robot.commands.sandstorm;

import com.thegongoliers.commands.RotateToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.AlignToFrontTarget;
import frc.robot.commands.DepositHatch;
import frc.robot.commands.DisableTargetMode;
import frc.robot.commands.EnableTargetMode;
import frc.robot.commands.FollowPathDrivetrain;
import frc.robot.commands.ForwardDrivetrain;
import frc.robot.paths.*;

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

    // Enable target mode
    addSequential(new EnableTargetMode());

    // Drive from HAB to cargo bay
    addSequential(new FollowPathDrivetrain(PathRightHAB1ToFrontRightHatch.leftPoints, PathRightHAB1ToFrontRightHatch.rightPoints));
    
    // Align and deposit hatch
    addSequential(new AlignToFrontTarget());
    addSequential(new ForwardDrivetrain(), 1); // TODO: Switch this to a path that drives 2.5 feet
    addSequential(new DepositHatch());
    
    // Drive from cargo bay to station
    addSequential(new FollowPathDrivetrain(PathBackupFromFrontRightHatch.leftPoints, PathBackupFromFrontRightHatch.rightPoints));
    addSequential(new RotateToAngle(Robot.drivetrain, 90));
    addSequential(new FollowPathDrivetrain(PathToRightStation.leftPoints, PathToRightStation.rightPoints));
    
    // Align with station and grab hatch
    addSequential(new AlignToFrontTarget());
    addSequential(new ForwardDrivetrain(), 1); // TODO: Switch this to a path that drives 2.5 feet

    // Drive from station to cargo bay
    addSequential(new FollowPathDrivetrain(PathBackupFromLeftStation.leftPoints, PathBackupFromLeftStation.rightPoints));
    addSequential(new RotateToAngle(Robot.drivetrain, 90));
    addSequential(new FollowPathDrivetrain(PathToRightSideHatch.leftPoints, PathToRightSideHatch.rightPoints));
    
    // Align and depsoit hatch
    addSequential(new AlignToFrontTarget());
    addSequential(new ForwardDrivetrain(), 1); // TODO: Switch this to a path that drives 2.5 feet
    addSequential(new DepositHatch());

    // Disable target mode
    addSequential(new DisableTargetMode());
    
  }
}
