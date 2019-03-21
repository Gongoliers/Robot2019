package frc.robot;

import java.util.List;

import com.kylecorry.frc.vision.camera.CameraSettings;
import com.kylecorry.frc.vision.camera.FOV;
import com.kylecorry.frc.vision.camera.Resolution;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.vision.CargoBayDetector;
import frc.robot.vision.TargetFinderFactory;
import frc.robot.vision.VideoCameraVisionTargetDetector;
import frc.robot.vision.VisionTarget;
import frc.robot.vision.VisionTargetDetector;

/**
 * The target finding vision system is controlled from within this class.
 */
public class Vision extends Subsystem {

    // Driver camera
    private static final Resolution DRIVER_CAMERA_RESOLUTION = new Resolution(640, 480);

    // Target camera
    private static final Resolution TARGET_CAMERA_RESOLUTION = new Resolution(320, 240);
    private static final FOV TARGET_CAMERA_VIEW_ANGLES = new FOV(61, 34.3);
    private static final boolean TARGET_CAMERA_INVERTED = false;

    public UsbCamera driverCamera;
    public UsbCamera targetingCamera;

    private CameraSettings cameraSettings;
    private CargoBayDetector targetDetector;

    public VisionTarget lastFoundTarget;


    public Vision() {
        // Initialize the driver camera
        driverCamera = CameraServer.getInstance().startAutomaticCapture("Driver camera", RobotMap.driverCamera);
        driverCamera.setResolution(DRIVER_CAMERA_RESOLUTION.getWidth(), DRIVER_CAMERA_RESOLUTION.getHeight());

        // Initialize the targeting camera
        targetingCamera = new UsbCamera("Targeting camera", RobotMap.targetingCamera);
        targetingCamera.setResolution(TARGET_CAMERA_RESOLUTION.getWidth(), TARGET_CAMERA_RESOLUTION.getHeight());
        enableTargetMode(targetingCamera);
        cameraSettings = new CameraSettings(TARGET_CAMERA_INVERTED, TARGET_CAMERA_VIEW_ANGLES, TARGET_CAMERA_RESOLUTION);
        VisionTargetDetector visionTargetDetector = new VideoCameraVisionTargetDetector(targetingCamera, cameraSettings, TargetFinderFactory.getCargoShipTargetFinder(cameraSettings));
        targetDetector = new CargoBayDetector(visionTargetDetector);
    }

    @Override
    public void periodic() {
        if (lastFoundTarget != null){
            SmartDashboard.putNumber("Target Angle", lastFoundTarget.getHorizontalAngle());
            SmartDashboard.putNumber("Target Distance", lastFoundTarget.getDistance());
        }
    }

    /**
     * Detects the vision targets.
     *
     * @return The vision targets sorted by percent area (largest to smallest).
     */
    public List<VisionTarget> detectTargets() {
        return targetDetector.getTargets();
    }

    /**
     * Enable targeting mode on a camera (low exposure).
     * @param camera the camera to enable targeting mode on
     */
    public void enableTargetMode(UsbCamera camera) {
        if (camera == null){
            return;
        }
        camera.setBrightness(0);
        camera.setExposureManual(0);
        // camera.setWhiteBalanceManual(value); TODO: Test to see if this is necessary or not
    }

    /**
     * Disable targeting mode on a camera (auto exposure).
     * @param camera the camera to disable targeting mode on
     */
    public void disableTargetMode(UsbCamera camera) {
        if (camera == null){
            return;
        }
        camera.setBrightness(50);
        camera.setExposureAuto();
        camera.setWhiteBalanceAuto();
    }

    /**
     * Switches the CameraServer stream to the driving camera
     *
     * CURRENTLY DOES NOTHING
     */
    public void switchToDriverCamera() {
        // cameraServer.setSource(driverCamera);
    }

    @Deprecated
    /**
     * Switches the CameraServer stream to the targeting camera
     *
     * CURRENTLY DOES NOTHING
     */
    public void switchToTargetingCamera() {
        // cameraServer.setSource(targetingCamera);
    }

    @Override
    protected void initDefaultCommand() {
        // No default command
    }

}
