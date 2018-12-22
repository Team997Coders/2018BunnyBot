/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
//import frc.robot.commands.BackHopperIdle;

/**
 * Add your docs here.
 */
public class BackHopper extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //public VictorSP motor; 
  public boolean backHopperState = false;

  public BackHopper() {
    //motor = new VictorSP(RobotMap.Ports.backHopperPort);
  }

  public void setVolts(double volts){
    //motor.set(volts);
  }
  
  public void stopVolts(){
    //motor.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand(new BackHopperIdle());
  }
  public void updateSmartDashboard(){
    SmartDashboard.putData("Enable BackHopper", new BackHopper());
  }
}
