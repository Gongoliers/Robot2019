package frc.robot.commands.drivetrain;

import com.thegongoliers.pathFollowing.FollowPathCommand;
import com.thegongoliers.pathFollowing.Path;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

/**
 * DriveForward
 */
public class DriveForward extends CommandGroup {

    /**
     * Drive forward a distance
     * @param distanceMeters the distance in meters
     */
    public DriveForward(double distanceMeters){
        Path path = new Path(Robot.drivetrain);
        path.addStraightAway(distanceMeters, 1.2);
        FollowPathCommand pathCommand = new FollowPathCommand(path);

        addSequential(pathCommand);
    }
    
}