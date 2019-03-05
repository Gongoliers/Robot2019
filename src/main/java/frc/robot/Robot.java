package frc.robot;

import com.kylecorry.frc.vision.targeting.Target;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.StopEverything;
import frc.robot.commands.sandstorm.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {

    public static final double PISTON_TIMEOUT = 0.2;

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    
    public static Drivetrain drivetrain;
    public static HatchManipulator hatchManipulator;
    public static CargoManipulator cargoManipulator;
    public static HABClimber habClimber;

    public static Compressor compressor;

    public static UsbCamera frontCamera;
    public static UsbCamera rearCamera;
    public static VideoSink cameraServer;
    public static Vision vision;
    public static CvSink cameraSink;
    public static Target lastFoundTarget;
    private static Mat image;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

        drivetrain = new Drivetrain();
        hatchManipulator = new HatchManipulator();
        cargoManipulator = new CargoManipulator();
        habClimber = new HABClimber();

        compressor = new Compressor();
        compressor.start();

        frontCamera = CameraServer.getInstance().startAutomaticCapture(0);
        rearCamera = CameraServer.getInstance().startAutomaticCapture(1);
        image = new Mat();
        cameraServer = CameraServer.getInstance().getServer();
        cameraServer.setSource(frontCamera);
        cameraSink = CameraServer.getInstance().getVideo();
        vision = new Vision();

        oi = new OI();

        chooser.setDefaultOption("DO NOTHING", new StopEverything());
        chooser.addOption("L1 2x H", new AutoLeftHAB1DeliverTwoHatches());
        chooser.addOption("M1 2x H", new AutoMiddleHAB1DeliverTwoHatches());
        chooser.addOption("R1 2x H", new AutoRightHAB1DeliverTwoHatches());

        SmartDashboard.putData("Auto mode", chooser);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * Switches the CameraServer stream to the front camera (hatch targeting)
     */
    public static void switchToFrontCamera() {
        cameraServer.setSource(frontCamera);
    }

    /**
     * Switches the CameraServer steeam to the rear camera (cargo targeting)
     */
    public static void switchToRearCamera() {
        cameraServer.setSource(rearCamera);
    }

    public static Mat getImage() {
        cameraSink.grabFrame(image);
        return image;
    }

}
