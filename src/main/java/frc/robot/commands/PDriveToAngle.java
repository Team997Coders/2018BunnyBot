/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.RobotMap;


public class PDriveToAngle extends Command {
 private double SetPoint;
private double error = 5;
  public PDriveToAngle(double setPoint) {
    requires(Robot.driveTrain);
    SetPoint = setPoint;
    
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (SetPoint >= Robot.driveTrain.getAngle()){
      Robot.driveTrain.setVolts( RobotMap.Values.DriveP * (SetPoint - Robot.driveTrain.getAngle()) , - (RobotMap.Values.DriveP * (SetPoint- Robot.driveTrain.getAngle())));
    } 
    else if (SetPoint <= Robot.driveTrain.getAngle()){
      Robot.driveTrain.setVolts(-(RobotMap.Values.DriveP * (SetPoint - Robot.driveTrain.getAngle())), (RobotMap.Values.DriveP * (SetPoint - Robot.driveTrain.getAngle())));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Math.abs(Robot.driveTrain.getAngle()) < (SetPoint + 5)){
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
