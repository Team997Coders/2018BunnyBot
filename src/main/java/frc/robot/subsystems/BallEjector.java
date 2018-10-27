/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * Add your docs here.
 */
public class BallEjector extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
 private VictorSP ballEjectorMotor;
 
 public BallEjector(){
  // TODO: need to input the motor controller (not avalible as of 5:52 pm 10/19/2018)
  // TODO: assign correct port information for the ballEjectorMotor in RobotMap.java
  ballEjectorMotor = new VictorSP(RobotMap.Ports.ballEjectorMotor);
  }
  public void setVolts(double B){
    if(B < 0){
      ballEjectorMotor.set(B);
    }else{
      ballEjectorMotor.set(-B);
    }
  }
  public void stopVolts(){
    ballEjectorMotor.set(0);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
