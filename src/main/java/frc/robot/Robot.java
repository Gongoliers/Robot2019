package frc.robot;

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

    public static Vision vision;

    public static Compressor compressor;

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

}
