/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.commands.*;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveTrain extends Subsystem {

  private VictorSP leftMotor, rightMotor;
  private Encoder leftEncoder, rightEncoder;
  public double lastGearNum;
  private DoubleSolenoid shiftSolenoid;
  public double leftRate;
  public double rightRate;
  //public DoubleSolenoid.Value currentGearNum = shiftSolenoid.get();
  public int gear = 0;

  public DriveTrain() {
    lastGearNum = 0;
    leftMotor = new VictorSP(RobotMap.Ports.leftMotorPort);
    rightMotor = new VictorSP(RobotMap.Ports.rightMotorPort);
    leftEncoder = new Encoder(RobotMap.Ports.leftEncoderChannelA, RobotMap.Ports.leftEncoderChannelB);
    rightEncoder = new Encoder(RobotMap.Ports.rightEncoderChannelA, RobotMap.Ports.rightEncoderChannelB);
    rightEncoder.setReverseDirection(true);
    leftEncoder.setDistancePerPulse(RobotMap.Values.ticksPerFoot);
    rightEncoder.setDistancePerPulse(RobotMap.Values.ticksPerFoot);
    shiftSolenoid = new DoubleSolenoid(RobotMap.Ports.gearPistonFor, RobotMap.Ports.gearPistonRev);
    updateSmarts();
  }


  public void setGear(double gearNum) {
    if (gearNum == 0 && lastGearNum != 0){
      shiftSolenoid.set(Value.kForward);
      lastGearNum = 0;
      System.out.println(lastGearNum);
    } else {
      shiftSolenoid.set(Value.kReverse);
      lastGearNum = 1;
      System.out.println(lastGearNum);
    }
    leftEncoder.setDistancePerPulse(1/7565);
    rightEncoder.setDistancePerPulse(1/7565);
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getLeftRate() {
    if (Math.abs(leftEncoder.getRate()/(RobotMap.Values.ticksPerFoot)) < 20) {
        return leftEncoder.getRate();/*Robot.oi.getLeftYAxis())*/
    }else{
      System.out.println(leftEncoder.getRate());
      return 0;
    }
  }

  public double getRightRate() {
    if (Math.abs(rightEncoder.getRate()/(RobotMap.Values.ticksPerFoot)) < 20) {
        return rightEncoder.getRate();/*Robot.oi.getRightYAxis())*/
    }else{
      System.out.println(rightEncoder.getRate());
      return 0;
    }
  }

  public void automaticShifting(){
    if (getLeftRate() >= 6 && getRightRate() >= 6 && lastGearNum == 0 && Math.abs(Robot.oi.getLeftYAxis()) == 1){
    setGear(1);
    lastGearNum = 1;
  } else{}
}

  public void setVolts(double L, double R) {
    leftMotor.set(-L/2);
    rightMotor.set(R/2);
  }

  public void stopVolts() {
    leftMotor.set(0);
    rightMotor.set(0);
  }

  public int getLeftTicks() { 
    return leftEncoder.get(); 
  }

  public int getRightTicks() { 
    return rightEncoder.get(); 
  }
  
  public void resetTicks() {
    leftEncoder.reset();
    rightEncoder.reset();
  
  }
  public void updateSmarts() {
    SmartDashboard.putString("idk", "YEEET");
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive());
  }

  public void updateSmartDashboard() {
    SmartDashboard.putNumber("LeftEncoderCount", leftEncoder.get());
    SmartDashboard.putNumber("RightEncoderCount", rightEncoder.get());
    SmartDashboard.putNumber("LeftEncoderRate", getLeftRate());
    SmartDashboard.putNumber("RightEncoderRate", getRightRate());
    SmartDashboard.putNumber("Gear", lastGearNum);
  }
}