package frc.robot.vision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.targetConverters.TargetUtils;
import com.kylecorry.frc.vision.targeting.Target;
import com.kylecorry.frc.vision.targeting.TargetFinder;
import com.thegongoliers.input.vision.ImageEditor;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class VideoCameraVisionTargetDetector implements VisionTargetDetector {

    private CvSink imageSink;
    private CvSource display;
    private TargetFinder targetFinder;
    private CameraSettings cameraSettings;
    private Mat image;
    private double distanceCoefficient;
    private double distanceOffset;

    private static final boolean shouldDisplay = true;

    /**
     * Constructs a video camera vision target detector, using the Robot Vision API.
     * @param camera the video camera
     * @param cameraSettings the camera's settings
     * @param targetFinder the target finder
     * @param distanceCoefficient the conversion of 1 - percent area to distance (0 is closest, 1 is furthest). The units of this value are units / percent area. Defaults to 1.
     * @param distanceOffset the distance the target is at when its percent area is 100% (distance = 0). Defaults to 0.
     */
    public VideoCameraVisionTargetDetector(final VideoCamera camera, final CameraSettings cameraSettings, final TargetFinder targetFinder, double distanceCoefficient, double distanceOffset){
        imageSink = new CvSink("Targeting sink");
        imageSink.setSource(camera);
        if (shouldDisplay){
            display = CameraServer.getInstance().putVideo("Target", 320, 240);
        }
        this.targetFinder = targetFinder;
        this.cameraSettings = cameraSettings;
        image = new Mat();
        this.distanceCoefficient = distanceCoefficient;
        this.distanceOffset = distanceOffset;
    }

    /**
     * Constructs a video camera vision target detector, using the Robot Vision API.
     * @param camera the video camera
     * @param cameraSettings the camera's settings
     * @param targetFinder the target finder
     */
    public VideoCameraVisionTargetDetector(final VideoCamera camera, final CameraSettings cameraSettings, final TargetFinder targetFinder){
        this(camera, cameraSettings, targetFinder, 1, 0);
    }

    /**
     * @see frc.robot.vision.VisionTargetDetector#getTargets()
     */
    @Override
    public List<VisionTarget> getTargets() {
        imageSink.grabFrame(image);
    
        List<Target> targets = targetFinder.findTargets(image);
        // Sort the targets by x coordinates
        targets.sort(Comparator.comparingDouble(target -> target.getBoundary().center.x));
 
        List<Target> bays = new ArrayList<>();
        // If the current target is a left and the next is a right, make it a pair
        for (int i = 0; i < targets.size() - 1; i++) {
            Target current = targets.get(i);
            Target next = targets.get(i + 1);

            // Determine if the targets are a left and right pair
            if (isLeftTarget(current) && isRightTarget(next)) {
                // Combine the targets
                Target bay = TargetUtils.combineTargets(current, next, cameraSettings);
                bays.add(bay);
                // Skip the next target
                i++;
            }
        }

        // Sort by target size
        bays.sort(Comparator.comparingDouble(Target::getPercentArea));

        Collections.reverse(bays);

        if (shouldDisplay){
            System.out.println("CALLED VISION GET TARGETS");
            Mat im = TargetFinderFactory.getTargetFilter().filter(image);
            for (Target bay: bays){
                ImageEditor.drawRectangleToMat(im, bay.getBoundary().boundingRect(), new Scalar(0, 0, 255));
            }
            display.putFrame(im);
        }

        List<VisionTarget> visionTargets = new ArrayList<>();

        for(Target target: bays){
            visionTargets.add(new VisionTarget(target.getHorizontalAngle(), target.getVerticalAngle(), 1 - (target.getPercentArea() * distanceCoefficient / 100.0) + distanceOffset));
        }

		return visionTargets;
    }
    
     /**
     * Determines if a target is a left vision target.
     * 
     * @param target The target.
     * @return True if it is a left target.
     */
    private boolean isLeftTarget(Target target) {
        return target.getSkew() < 0;
    }

    /**
     * Determines if a target is a right vision target.
     * 
     * @param target The target.
     * @return True if it is a right target.
     */
    private boolean isRightTarget(Target target) {
        return target.getSkew() > 0;
    }

}