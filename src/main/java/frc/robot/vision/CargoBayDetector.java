package frc.robot.vision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.kylecorry.frc.vision.Range;
import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.contourFilters.StandardContourFilter;
import com.kylecorry.frc.vision.filters.HSVFilter;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.frc.vision.targetConverters.TargetGrouping;
import com.kylecorry.frc.vision.targetConverters.TargetUtils;
import com.kylecorry.frc.vision.targeting.Target;
import com.kylecorry.frc.vision.targeting.TargetFinder;

import org.opencv.core.Mat;

/**
 * A detector of cargo bay vision targets.
 */
public class CargoBayDetector implements VisionTargetDetector {

    private CameraSettings cameraSettings;
    private TargetFinder targetFinder;


    public CargoBayDetector(final CameraSettings cameraSettings){
         // Contour filter parameters
         Range area = new Range(0.03, 100);
         Range fullness = new Range(0, 100);
         Range aspectRatio = new Range(0.2, 4);
  
         int imageArea = cameraSettings.getResolution().getArea();

        TargetFilter filter = new HSVFilter(new Range(70*180/255D, 130*180/255D), new Range(130, 255), new Range(65, 255));
        ContourFilter contourFilter = new StandardContourFilter(area, fullness, aspectRatio, imageArea);
        this.cameraSettings = cameraSettings;
        targetFinder = new TargetFinder(cameraSettings, filter, contourFilter, TargetGrouping.SINGLE);
    }


    @Override
    /**
     * @see VisionTargetDetector.detect
     */
	public List<Target> detect(Mat image) {
		 // Find the targets
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
 
         // TODO: Switch to this if needed
         // Sort by distance to center
         // bays.sort(Comparator.comparingDouble((target) -> Math.abs(target.getHorizontalAngle())));
 
         Collections.reverse(bays);
         return bays;
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