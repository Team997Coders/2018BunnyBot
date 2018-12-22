/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
//import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.commands.FrontHopperEnabled;

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
  public void setVolts(double volts){
    frontHopperMotor1.set(ControlMode.PercentOutput, volts);
    frontHopperMotor2.set(ControlMode.PercentOutput, volts);
  }
  
  public void stopVolts(){
    frontHopperMotor1.set(ControlMode.PercentOutput, 0);
    frontHopperMotor2.set(ControlMode.PercentOutput, 0);
  }

 
  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new FrontHopperEnabled());
  }
  
  public void updateSmartDashboard(){
    SmartDashboard.putData("Enable FrontHopper", new FrontHopper());
  }
}
