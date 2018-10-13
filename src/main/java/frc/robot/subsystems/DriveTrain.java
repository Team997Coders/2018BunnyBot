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
import frc.robot.RobotMap;

public class DriveTrain extends Subsystem {

  private VictorSP leftMotor, rightMotor;
  private Encoder leftEncoder, rightEncoder;
  public double lastGearNum = 0;
  private DoubleSolenoid shiftSolenoid;

  public int gear = 0;

  public DriveTrain() {
    leftMotor = new VictorSP(RobotMap.Ports.leftMotorPort);
    rightMotor = new VictorSP(RobotMap.Ports.rightMotorPort);
    leftEncoder = new Encoder(RobotMap.Ports.leftEncoderChannelA, RobotMap.Ports.leftEncoderChannelB);
    rightEncoder = new Encoder(RobotMap.Ports.rightEncoderChannelA, RobotMap.Ports.rightEncoderChannelB);
    rightEncoder.setReverseDirection(true);
    shiftSolenoid = new DoubleSolenoid(RobotMap.Ports.gearPistonFor, RobotMap.Ports.gearPistonRev);
    updateSmarts();
  }


  public void setGear(double gearNum) {
    if (gearNum == 1 && lastGearNum != 1){
      shiftSolenoid.set(Value.kForward);
    } else {
      shiftSolenoid.set(Value.kReverse);
    }
  }

  public void setVolts(double L, double R) {
    leftMotor.set(-L);
    rightMotor.set(R);
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

}