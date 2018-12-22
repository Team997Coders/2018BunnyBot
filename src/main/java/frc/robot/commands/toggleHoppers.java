/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class toggleHoppers extends Command {

  static boolean state = false;

  public toggleHoppers() {
    //TODO: EXTREMELY JANK
    requires(Robot.frontHopper);
    //requires(Robot.backHopper);
    requires(Robot.sorter);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    if (state) {
      state = false;
    } else {
      state = true;
    }
  }

  public toggleHoppers(boolean _state) {
    //requires(Robot.frontHopper);
    //requires(Robot.backHopper);
    //requires(Robot.sorter);

    state = _state;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Robot.frontHopper.setVolts(-.48);
    //Robot.backHopper.setVolts(-.48);
    //Robot.sorter.setMotor(-0.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if (!state) {
      //Robot.frontHopper.stopVolts();
      //Robot.backHopper.stopVolts();
      //Robot.sorter.stopMotors();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //Robot.frontHopper.stopVolts();
    //Robot.backHopper.stopVolts();
  }
}
