/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class BunnyEjector extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DoubleSolenoid bunnyEjectorSolenoid;

 
  public boolean ejected = false;
  
  public BunnyEjector(){
    
    
    bunnyEjectorSolenoid = new DoubleSolenoid(RobotMap.Ports.bunnyEjectorSolenoidPort1, RobotMap.Ports.bunnyEjectorSolenoidPort2); 
    bunnyEjectorSolenoid.set(Value.kReverse);
    //ejected = this.ejectedState;
  }
  public void ejectBunny(){
    bunnyEjectorSolenoid.set(DoubleSolenoid.Value.kForward);
    ejected = true;
    try {
      Thread.sleep(500);
      bunnyEjectorSolenoid.set(Value.kReverse);
    } catch (Exception e) {}
  }
  public void stopEjector(){
    bunnyEjectorSolenoid.set(Value.kReverse);
    
    

  }
  
  @Override
  public void initDefaultCommand() {
  }

  public void bunnyEjectorDashBoard(){
    SmartDashboard.putBoolean("BunnyState", ejected);
  }
}
