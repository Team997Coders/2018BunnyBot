/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.VictorSP;



public class FrontHopper extends Subsystem {
 
 public VictorSPX frontHopperMotor1;
 public VictorSPX frontHopperMotor2;

  public FrontHopper() {
    frontHopperMotor1 = new VictorSPX(RobotMap.Ports.FrontHopperMotorPort1);
    frontHopperMotor2 = new VictorSPX(RobotMap.Ports.FrontHopperMotorPort2);
    frontHopperMotor2.configPeakOutputForward(1, 10);
    //frontHopperMotor1.configPeakOutPut(1.0);
    frontHopperMotor1.configPeakOutputForward(1, 10);


  }

 
  @Override
  public void initDefaultCommand() {}
  
  public void updateSmartDashboard(){
    SmartDashboard.putData("Enable FrontHopper", new FrontHopper());
  }
}
