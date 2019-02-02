package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  // Drivetrain Subsystem
  public static final int leftMotor = 1;
  public static final int leftSlave1 = 2;
  public static final int leftSlave2 = 3;

  public static final int rightMotor = 4;
  public static final int rightSlave1 = 5;
  public static final int rightSlave2 = 6;

  // Cargo Subsystem
  public static final int cargoPiston1 = 0;
  public static final int cargoPiston2 = 1;

  public static final int cargoMotor1 = 7;
  public static final int cargoMotor2 = 8;
  public static final int cargoMotor3 = 9;

  public static final int cargoLimitSwitch1 = 0;
  public static final int cargoLimitSwitch2 = 1;
  public static final int cargoLimitSwitch3 = 2;

  // HAB Climber Subsystem
  public static final int climberPiston1 = 2;
  public static final int skidPiston1 = 3;
  public static final int skidPiston2 = 4;
  
  // Hatch Subsystem
  public static final int hatchPiston1 = 5;
  public static final int hatchPiston2 = 6;

  public static final int hatchMotor1 = 10;

  public static final int topLimitSwitchHatch = 0;
  public static final int bottomLimitSwitchHatch = 0;
  

}
