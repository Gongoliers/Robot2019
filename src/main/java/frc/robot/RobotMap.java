/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  // TODO: Make all constants final

  // Drivetrain Subsystem
  public static int leftMotor = 1;
  public static int leftSlave1 = 2;
  public static int leftSlave2 = 3;

  public static int rightMotor = 4;
  public static int rightSlave1 = 5;
  public static int rightSlave2 = 6;

  // Cargo Subsystem
  public static final int cargoPiston1 = 0;
  public static final int cargoPiston2 = 1;
  public static final int cargoMotor1 = 7;
  public static final int cargoMotor2 = 8;
  public static final int cargoMotor3 = 9;

  // Cargo Limit Switches
  public static final int cargoLimitSwitch1 = 0;
  public static final int cargoLimitSwitch2 = 1;
  public static final int cargoLimitSwitch3 = 2;

  // Climber Manipulator
  public static final int climberPiston1 = 2;
  public static final int skidPiston1 = 3;
  public static final int skidPiston2 = 4;
  
  // Hatch Manipulator
  public static final int hatchPiston1 = 5;
  public static final int hatchPiston2 = 6;

  public static final int hatchMotor1 = 10;
  

}
