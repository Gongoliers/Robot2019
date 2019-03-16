package frc.robot.commands.vision;

import java.util.List;

import com.kylecorry.frc.vision.targeting.Target;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class LocateFrontTarget extends InstantCommand {

    @Override
    protected void initialize() {
        List<Target> targets = Robot.vision.detectTargets();
        if (!targets.isEmpty()) {
            Robot.vision.lastFoundTarget = targets.get(0);
        } else {
            Robot.vision.lastFoundTarget = null;
        }
    }

}
