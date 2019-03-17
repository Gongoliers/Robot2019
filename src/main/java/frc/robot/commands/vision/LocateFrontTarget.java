package frc.robot.commands.vision;

import java.util.List;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.vision.VisionTarget;

public class LocateFrontTarget extends InstantCommand {

    @Override
    protected void initialize() {
        List<VisionTarget> targets = Robot.vision.detectTargets();
        if (!targets.isEmpty()) {
            Robot.vision.lastFoundTarget = targets.get(0);
        } else {
            Robot.vision.lastFoundTarget = null;
        }
    }

}
