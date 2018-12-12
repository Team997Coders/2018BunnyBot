/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveToDistance extends Command {
  private double leftDistance;
  private double rightDistance;

  private boolean isReset = false;

  public DriveToDistance(double leftTicks, double rightTicks) {
    requires(Robot.driveTrain);
    leftDistance = leftTicks;
    rightDistance = rightTicks;

    isReset = false;
  }


  @Override
  protected void initialize() {
    Robot.driveTrain.resetEncoders();
    
    isReset = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Robot.driveTrain.setVolts(.1, .1);
    
    // TEMP
    Robot.driveTrain.setMotorToPosition(leftDistance, rightDistance);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
    /*if (!isReset) {
      Robot.driveTrain.resetEncoders();
      isReset = true;
      return false;
    }

    if (Robot.driveTrain.getLeftEncoderTicks() >= -leftDistance || Robot.driveTrain.getRightEncoderTicks()>= -rightDistance){
      //System.out.println("Yo We done");
      //System.out.println(Robot.driveTrain.getLeftEncoderTicks() + " " + Robot.driveTrain.getRightEncoderTicks());
      //return true;
    }
    return false;*/
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
