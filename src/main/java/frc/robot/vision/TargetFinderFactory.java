package frc.robot.vision;

import com.kylecorry.frc.vision.Range;
import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.contourFilters.StandardContourFilter;
import com.kylecorry.frc.vision.filters.HSVFilter;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.frc.vision.targetConverters.TargetGrouping;
import com.kylecorry.frc.vision.targeting.TargetFinder;

public final class TargetFinderFactory {

    private TargetFinderFactory(){}

    /**
     * Get the cargo ship target finder
     * @param cameraSettings the camera's settings
     * @return the cargo ship target finder
     */
    public static TargetFinder getCargoShipTargetFinder(CameraSettings cameraSettings){
        Range area = new Range(0.03, 100);
        Range fullness = new Range(0, 100);
        Range aspectRatio = new Range(0.2, 4);
  
        int imageArea = cameraSettings.getResolution().getArea();

        TargetFilter filter = getTargetFilter();
        ContourFilter contourFilter = new StandardContourFilter(area, fullness, aspectRatio, imageArea);
        return new TargetFinder(cameraSettings, filter, contourFilter, TargetGrouping.SINGLE);
    }


    public static TargetFilter getTargetFilter(){
        return new HSVFilter(new Range(70*180/255D, 130*180/255D), new Range(130, 255), new Range(65, 255));
    }

}