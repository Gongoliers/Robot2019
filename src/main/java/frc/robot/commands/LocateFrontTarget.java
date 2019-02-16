package frc.robot.commands;

import java.util.List;

import com.kylecorry.frc.vision.targeting.Target;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class LocateFrontTarget extends InstantCommand {

    @Override
    protected void initialize() {
        Robot.switchToFrontCamera();
        Robot.vision.enableTargetMode(Robot.frontCamera);
        List<Target> targets = Robot.vision.detectTargets(Robot.getImage());
        if (!targets.isEmpty()) {
            Robot.lastFoundTarget = targets.get(0);
        } else {
            Robot.lastFoundTarget = null;
        }
    }

}
