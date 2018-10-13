/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.RobotMap;

public class DriveTrain extends Subsystem {

  private VictorSP leftMotor, rightMotor;
  private Encoder leftEncoder, rightEncoder;

  public DriveTrain() {
    leftMotor = new VictorSP(RobotMap.Ports.leftMotorPort);
    rightMotor = new VictorSP(RobotMap.Ports.rightMotorPort);
    leftEncoder = new Encoder(RobotMap.Ports.leftEncoderChannelA, RobotMap.Ports.leftEncoderChannelB);
    rightEncoder = new Encoder(RobotMap.Ports.rightEncoderChannelA, RobotMap.Ports.rightEncoderChannelB);
    rightEncoder.setReverseDirection(true);
    leftEncoder.setDistancePerPulse(RobotMap.Values.ticksPerFoot);
    rightEncoder.setDistancePerPulse(RobotMap.Values.ticksPerFoot);
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public void setVolts(double L, double R) {
    leftMotor.set(-L/10);
    rightMotor.set(R/10);
  }

  public void stopVolts() {
    leftMotor.set(0);
    rightMotor.set(0);
  }

  public int getLeftTicks() { return leftEncoder.get(); }

  public int getRightTicks() { return rightEncoder.get(); }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TankDrive());
  }
  public void UpdateSmartDashboard(){
    SmartDashboard.putNumber("LeftEncoderCount", leftEncoder.get());
    SmartDashboard.putNumber("RightEncoderCount", rightEncoder.get());

  }
}