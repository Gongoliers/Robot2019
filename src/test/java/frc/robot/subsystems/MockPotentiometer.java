/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 * Add your docs here.
 */
public class MockPotentiometer implements Potentiometer {

    private double value;
    private PIDSourceType sourceType;

    public MockPotentiometer(){
        value = 0;
        sourceType = PIDSourceType.kDisplacement;
    }

    /**
     * Sets the value of the potentiometer.
     * @param value The value of the potentiometer.
     */
    public void set(double value){
        this.value = value;
    }

    /**
     * Gets the value of the potentiometer.
     */
    public double get(){
        return value;
    }   

    /**
   * Set which parameter of the device you are using as a process control variable.
   *
   * @param pidSource An enum to select the parameter.
   */
  public void setPIDSourceType(PIDSourceType pidSource){
    sourceType = pidSource;
  }

  /**
   * Get which parameter of the device you are using as a process control variable.
   *
   * @return the currently selected PID source parameter
   */
  public PIDSourceType getPIDSourceType(){
    return sourceType;
  }

  /**
   * Get the result to use in PIDController.
   *
   * @return the result to use in PIDController
   */
  public double pidGet(){
      return get();
  }

}
