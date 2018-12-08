/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
/**
 * Add your docs here.
 */
public class BallIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public TalonSRX intakeMotor;

  public BallIntake(){
    intakeMotor = new TalonSRX(RobotMap.Ports.intakeMotorPort); 
    /* set the peak, nominal outputs */
		intakeMotor.configNominalOutputForward(0, 10);
		intakeMotor.configNominalOutputReverse(0, 10);
		intakeMotor.configPeakOutputForward(1.0, 10);
		intakeMotor.configPeakOutputReverse(-1.0, 10);
  }
    
public void collect(){
  intakeMotor.set(ControlMode.PercentOutput, -1.0); //-1 because 1 is reversed
}

public void stop(){
  intakeMotor.set(ControlMode.PercentOutput, 0);
}

public void eject(){
  intakeMotor.set(ControlMode.PercentOutput, 1.0);
}
 
  @Override
  public void initDefaultCommand() {
  }
}
