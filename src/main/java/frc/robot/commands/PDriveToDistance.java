/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.RobotMap;

public class PDriveToDistance extends Command {
  private double Error;
  private double  initYaw = Robot.driveTrain.getAngle();
  public PDriveToDistance(double error) {
    requires(Robot.driveTrain);
    Error = error;
    
  
    
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() { 
    Robot.driveTrain.resetTicks();
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("RealInitYaw", initYaw);
    Robot.driveTrain.setVolts(RobotMap.Values.DriveP * (Error-Robot.driveTrain.GetAverageTicks()), RobotMap.Values.DriveP * (Error-Robot.driveTrain.GetAverageTicks()));
    if ((Robot.driveTrain.getAngle()) < (initYaw-Error)){
      Robot.driveTrain.setVolts(RobotMap.Values.DriveP * (Error-Robot.driveTrain.GetAverageTicks()), RobotMap.Values.DriveP * (Error-Robot.driveTrain.GetAverageTicks()));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Math.abs(Error-Robot.driveTrain.GetAverageTicks()) <= 75){
    return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
