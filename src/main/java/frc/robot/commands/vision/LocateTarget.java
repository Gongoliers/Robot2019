package frc.robot.commands.vision;

import java.util.List;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.vision.VisionTarget;

public class LocateTarget extends InstantCommand {
    
    @Override
    protected void initialize() {
        try {
            List<VisionTarget> targets = Robot.vision.detectTargets();
            if (!targets.isEmpty()) {
                Robot.vision.lastFoundTarget = targets.get(0);
            } else {
                Robot.vision.lastFoundTarget = null;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }   

}
