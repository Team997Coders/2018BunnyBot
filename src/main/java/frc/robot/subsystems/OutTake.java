/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.RobotMap;
import frc.robot.commands.OutTakeBalls;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class OutTake extends Subsystem {

  public VictorSP outTakeMotor;

  public OutTake(){
    outTakeMotor = new VictorSP(RobotMap.Ports.outtakeMotorPort);

  }
  public void shoot(){
    outTakeMotor.set(1.0);

  }
  public void stop(){
    outTakeMotor.set(0);

  }

  @Override
  public void initDefaultCommand() {
  }

public void updateSmartDashboard(){
    SmartDashboard.putData("OutTake", new OutTakeBalls());
}
