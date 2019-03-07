package frc.robot.commands.vision;

import java.util.List;

import com.kylecorry.frc.vision.targeting.Target;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class LocateRearTarget extends InstantCommand {
    
    @Override
    protected void initialize() {
        Robot.vision.switchToRearCamera();
        Robot.vision.enableTargetMode(Robot.vision.rearCamera);
        List<Target> targets = Robot.vision.detectTargets(Robot.vision.getImage());
        if (!targets.isEmpty()) {
            Robot.vision.lastFoundTarget = targets.get(0);
        } else {
            Robot.vision.lastFoundTarget = null;
        }
    }

}