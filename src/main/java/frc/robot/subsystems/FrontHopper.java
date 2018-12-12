/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.VictorSPX;
import edu.wpi.first.wipilibj.command.Subsystem;
import edu.wpi.first.wipilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;



public class FrontHopper extends Subsystem {
 
 public VictorSPX frontHopperMotor;

  public FrontHopper() {
    frontHopperMotor = new VictorSPX(RobotMap.Ports.FrontHopperMotorPort);
    
    frontHopperMotor.configPeakOutPut(1.0);


  }

  // Called just before this Command runs the first time
  @Override
  protected void run() {

    frontHopperMotor.set()ControlMode.PercentOutput,1.0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    frontnHopperMotor.set()ControlMode.PercentOUtput, 0);
  }


  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  
  @Override
  protected void interrupted() {
  }
  public void updateSmartDashboard(){
    SmartDashboard.putData("Enable FrontHopper", new FrontHopper());
  }
}
