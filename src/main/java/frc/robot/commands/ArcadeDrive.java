/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArcadeDrive extends Command {
  public ArcadeDrive() {
    requires(Robot.driveTrain);
  }
  
  @Override
  protected void initialize() {}

  @Override
  protected void execute() {
    double leftVolts = (Robot.oi.getLeftYAxis()) - (Robot.oi.getRightXAxis());
    double rightVolts = (Robot.oi.getLeftYAxis()) + (Robot.oi.getRightXAxis());
    Robot.driveTrain.setVolts(leftVolts, rightVolts);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
