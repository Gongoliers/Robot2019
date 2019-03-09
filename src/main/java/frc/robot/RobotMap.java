package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static final int POTENTIOMETER_RANGE_DEGREES = 3600;

  // Drivetrain Subsystem
  public static final int leftMotor = 4;
  public static final int leftSlave1 = 5;
  public static final int leftSlave2 = 6;

  public static final int rightMotor = 1;
  public static final int rightSlave1 = 2;
  public static final int rightSlave2 = 3;

  // Cargo Subsystem
  public static final int cargoPiston = 2;

  public static final int cargoWristMotor = 8;
  public static final int cargoRollerMotor = 9;

  public static final int cargoLimitSwitch = 0;
  public static final int cargoPotentiometer = 0;

  // HAB Climber Subsystem
  public static final int climberPiston = 3;
  public static final int skidPiston1 = 4;
  public static final int skidPiston2 = 5;

  // Hatch Subsystem
  public static final int hatchPiston = 0;

  public static final int hatchMotor = 7;

  public static final int hatchPotentiometer = 1;

}
