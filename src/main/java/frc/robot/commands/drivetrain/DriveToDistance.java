/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import com.thegongoliers.input.odometry.Odometry;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveToDistance extends Command {

    private double distance;
    private static final double TOLERANCE = 0.1;


    /**
     * Drives a certain distance in feet (forward).
     * 
     * @param distance The distance in feet.
     */
    public DriveToDistance(double distance) {
        requires(Robot.drivetrain);
        this.distance = Odometry.getDistance(Robot.drivetrain.getLeftDistance(), Robot.drivetrain.getRightDistance()) + distance;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        // Robot.drivetrain.resetDistance();
       distance += Odometry.getDistance(Robot.drivetrain.getLeftDistance(), Robot.drivetrain.getRightDistance());
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.drivetrain.driveToDistance(distance);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Math.abs(Odometry.getDistance(Robot.drivetrain.getLeftDistance(), Robot.drivetrain.getRightDistance()) - distance) <= TOLERANCE;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
