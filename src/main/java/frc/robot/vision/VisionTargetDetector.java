package frc.robot.vision;

import java.util.List;

import com.kylecorry.frc.vision.targeting.Target;

import org.opencv.core.Mat;

public interface VisionTargetDetector {

    /**
     * Detects vision targets within an image
     * @param image the image to look for targets within
     * @return the targets in the image sorted by size
     */
    List<Target> detect(Mat image);

}