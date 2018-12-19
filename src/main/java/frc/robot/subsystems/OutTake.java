/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class outTake extends Subsystem {

  public VictorSP outTakeMotor;

  public OutTake(){
    outTakeMotor = new VictorSP(RobotMap.Ports.outTakeMotorPort);

  }
  public void Shoot(){
    outTakeMotor.set(ControlMode.PercentOutPut, 1.0);

  }
  public void stop(){
    outTakeMotor.set(ControlMode.PercentOutPut, 0);

  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
public void updateSmartDashboard(){
    SmartDashboard.putData("OutTake", new OutTakeBalls());
}
