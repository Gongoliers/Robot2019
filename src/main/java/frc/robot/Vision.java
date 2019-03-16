package frc.robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.kylecorry.frc.vision.Range;
import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.camera.FOV;
import com.kylecorry.frc.vision.camera.Resolution;
import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.contourFilters.StandardContourFilter;
import com.kylecorry.frc.vision.filters.HSVFilter;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.frc.vision.targetConverters.TargetGrouping;
import com.kylecorry.frc.vision.targetConverters.TargetUtils;
import com.kylecorry.frc.vision.targeting.Target;
import com.kylecorry.frc.vision.targeting.TargetFinder;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The target finding vision system is controlled from within this class.
 */
public class Vision extends Subsystem {

    public UsbCamera driverCamera;
    public UsbCamera targetingCamera;
    public VideoSink cameraServer;
    public CvSink targetingSink;
    public Target lastFoundTarget;
    private Mat image;

    private TargetFilter filter;
    private ContourFilter contourFilter;
    private CameraSettings cameraSettings;
    private TargetFinder targetFinder;

    private static final Resolution DRIVER_CAMERA_RESOLUTION = new Resolution(640, 480);
    private static final Resolution TARGET_CAMERA_RESOLUTION = new Resolution(320, 240);
    private static final FOV TARGET_CAMERA_VIEW_ANGLES = new FOV(61, 34.3);

    public Vision() {
        // Initialize the driver camera
        driverCamera = CameraServer.getInstance().startAutomaticCapture(RobotMap.driverCamera);
        driverCamera.setResolution(DRIVER_CAMERA_RESOLUTION.getWidth(), DRIVER_CAMERA_RESOLUTION.getHeight());

        // Initialize the targeting camera
        targetingCamera = new UsbCamera("Targeting camera", RobotMap.targetingCamera);
        targetingCamera.setResolution(TARGET_CAMERA_RESOLUTION.getWidth(), TARGET_CAMERA_RESOLUTION.getHeight());
        targetingSink = new CvSink("Targeting sink");
        targetingSink.setSource(targetingCamera);
        enableTargetMode(targetingCamera);

        image = new Mat();
        

        // Contour filter parameters
        Range area = new Range(0.03, 100);
        Range fullness = new Range(0, 100);
        Range aspectRatio = new Range(0.2, 4);

        // Camera settings
        boolean cameraInverted = false;

        int imageArea = TARGET_CAMERA_RESOLUTION.getArea();

        // Create the camera filters
        filter = new HSVFilter(new Range(70*180/255D, 130*180/255D), new Range(130, 255), new Range(65, 255));
        contourFilter = new StandardContourFilter(area, fullness, aspectRatio, imageArea);
        cameraSettings = new CameraSettings(cameraInverted, TARGET_CAMERA_VIEW_ANGLES, TARGET_CAMERA_RESOLUTION);
        targetFinder = new TargetFinder(cameraSettings, filter, contourFilter, TargetGrouping.SINGLE);
    }

    @Override
    public void periodic() {
        if (lastFoundTarget != null){
            SmartDashboard.putNumber("Target Angle", lastFoundTarget.getHorizontalAngle());
            SmartDashboard.putNumber("Target Skew", lastFoundTarget.getSkew());
        }
    }

    /**
     * Detects the vision targets.
     * 
     * @return The list of vision target groups.
     */
    public List<Target> detectTargets() {

        targetingSink.grabFrame(image);

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

    public void enableTargetMode(UsbCamera camera) {
        if (camera == null){
            return;
        }
        camera.setBrightness(0);
        camera.setExposureManual(0);
        // camera.setWhiteBalanceManual(value); TODO: Test to see if this is necessary or not
    }

    public void disableTargetMode(UsbCamera camera) {
        if (camera == null){
            return;
        }
        camera.setBrightness(50);
        camera.setExposureAuto();
        camera.setWhiteBalanceAuto();
    }

    /**
     * Switches the CameraServer stream to the front camera (hatch targeting)
     */
    public void switchToDriverCamera() {
        cameraServer.setSource(driverCamera);
    }

    /**
     * Switches the CameraServer steeam to the rear camera (cargo targeting)
     */
    public void switchToTargetingCamera() {
        // cameraServer.setSource(targetingCamera);
    }

    @Override
    protected void initDefaultCommand() {
        // No default command
    }

}
