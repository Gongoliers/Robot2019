package frc.robot.commands;

import com.thegongoliers.commands.RotateToAngle;

import frc.robot.Robot;

/**
 * Rotates the robot drivetrain to a specific angle
 */
public class RotateToAngleDrivetrain extends RotateToAngle {

    public RotateToAngleDrivetrain(int degrees) {

        super(Robot.drivetrain, degrees); 

    }

}
